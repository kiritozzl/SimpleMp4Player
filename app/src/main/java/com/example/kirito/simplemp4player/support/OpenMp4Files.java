package com.example.kirito.simplemp4player.support;


import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import com.example.kirito.simplemp4player.entity.Mp4Item;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by kirito on 2016/9/10.
 */
public class OpenMp4Files {
    private List<Mp4Item> items;
    private Context mContext;
    private static final String TAG = "OpenMp4Files";

    public OpenMp4Files(Context context) {
        mContext = context;
        items = new ArrayList<>();
    }

    public List<Mp4Item> openMp4(String path){
        int i = 0;
        File file = new File(path);
        File [] files = file.listFiles();

        for (File f : files){
            if (!f.isDirectory()){
                String name = f.getName();
                if (name.endsWith(".mp4")){
                    Mp4Item item = new Mp4Item();
                    item.setName(name);
                    //Log.e(TAG, "openMp4: path---"+f.getAbsolutePath() );
                    item.setPath(f.getAbsolutePath());
                    item.setSize(calculateSize(f.length()));
                    item.setTime(getDuration("file://"+f.getAbsolutePath()));

                    items.add(item);
                }
            }else if (f.isDirectory()){
                openMp4(f.getAbsolutePath());
            }
        }
        return items;
    }

    //获取MP4文件的时长
    private String getDuration(String pt){
        String time = null;
        //Log.e(TAG, "getDuration: uri---"+Uri.parse(pt) );
        MediaPlayer mp = MediaPlayer.create(mContext, Uri.parse(pt));
        if (mp != null){
            int duration = mp.getDuration();
            mp.release();
            duration /= 1000;
            if (duration < 60){
                time = "00:00:" + transform(duration);
            }else if (duration >= 60 && duration < 60 * 60){
                int min = duration / 60;
                time = "00:" + transform(min) + ":"+ transform(duration % 60);
            }else {
                int hour = duration / 60 / 60;
                int min = 0;
                int sec = 0;
                if (duration - 3600 >= 60){
                    min = (duration - 3600) / 60;
                    sec = (duration - 3600) % 60;
                }else if (duration - 3600 < 60){
                    min = 00;
                    sec = duration - 3600;
                }
                time = transform(hour) + ":" + transform(min) + ":" + transform(sec);
            }
        }
        if (time == null){
            time = "00:00:00";
        }
        return time;
    }

    private String transform(int i){
        if (i >= 10){
            return i + "";
        }else if(i < 10){
            return "0" + i;
        }
        return null;
    }

    /*public String getLastModifyDate(File file){
        File [] fs = file.listFiles();
        long max_date = 100;

        for (File f : fs){
            if (f.lastModified() > max_date){
                max_date = f.lastModified();
            }
        }
        Date date = new Date(max_date);
        String last_modify_date = new SimpleDateFormat("yyyy,MM,dd HH:mm:ss").format(date);
        return last_modify_date;
    }*/

    /*public int getFileCount(File file){
        File [] fs = file.listFiles();
        int count = 0;
        for (File f : fs){
            count++;
        }
        return count;
    }*/

    public String calculateSize(long size){
        String file_size = "";
        if (size > 1024 && size <= 1024 * 1024){
            file_size = size + "KB";
        }else if (size > 1024 * 1024 && size <= 1024 * 1024 * 1024){
            file_size = size / 1024 / 1024 + "MB";
        }
        return file_size;
    }
}
