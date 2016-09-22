package com.example.kirito.simplemp4player.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kirito.simplemp4player.R;
import com.example.kirito.simplemp4player.entity.Mp4Item;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kirito on 2016/9/22.
 */
public class GridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<Mp4Item> items;

    public GridViewAdapter(Context mContext, List<Mp4Item> items) {
        this.mContext = mContext;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_items,null);
            holder = new viewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (viewHolder) convertView.getTag();
        }
        Mp4Item item = items.get(position);
        //获取视频文件缩略图
        Bitmap thumb = ThumbnailUtils.createVideoThumbnail(item.getPath(), MediaStore.Images.Thumbnails.MINI_KIND);
        if (thumb != null){
            holder.iv.setImageBitmap(thumb);
        }
        holder.tv_name.setText(item.getName());
        holder.tv_size.setText(item.getSize());
        holder.tv_time.setText(item.getTime());
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    class viewHolder{
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_size)
        TextView tv_size;
        @BindView(R.id.tv_time)
        TextView tv_time;

        public viewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }
}
