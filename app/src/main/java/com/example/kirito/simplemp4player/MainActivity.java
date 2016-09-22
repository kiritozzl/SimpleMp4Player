package com.example.kirito.simplemp4player;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.example.kirito.simplemp4player.adapter.GridViewAdapter;
import com.example.kirito.simplemp4player.entity.Mp4Item;
import com.example.kirito.simplemp4player.support.LoadMp4;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private String path;
    @BindView(R.id.gv) GridView gv;

    private GridViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        path = "/storage/sdcard1";
        loadData();
    }

    private void loadData(){
        LoadMp4 loadMp4 = new LoadMp4(this);
        loadMp4.setCallBack(new LoadMp4.CallBack() {
            @Override
            public void setListItem(final List<Mp4Item> listItem) {
                adapter = new GridViewAdapter(MainActivity.this,listItem);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        gv.setAdapter(adapter);
                    }
                });

            }
        });
        loadMp4.execute(path);
    }
}
