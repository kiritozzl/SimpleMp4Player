package com.example.kirito.simplemp4player.support;

import android.content.Context;
import android.os.AsyncTask;


import com.example.kirito.simplemp4player.entity.Mp4Item;

import java.util.List;

/**
 * Created by kirito on 2016/9/10.
 */
public class LoadMp4 extends AsyncTask<String,Void,Void>{
    private CallBack callBack;
    private ShowProgress dialog;

    private Context mContext;

    public LoadMp4(Context context) {
        mContext = context;
        dialog = new ShowProgress(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.show();
    }

    @Override
    protected Void doInBackground(String... params) {
        List<Mp4Item> itemList = new OpenMp4Files(mContext).openMp4(params[0]);
        if (callBack != null){
            callBack.setListItem(itemList);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void mVoid) {
        super.onPostExecute(mVoid);
        dialog.dismiss();
    }

    public void setCallBack(CallBack callBack){
        this.callBack = callBack;
    }

    public interface CallBack{
        void setListItem(List<Mp4Item> listItem);
    }


}
