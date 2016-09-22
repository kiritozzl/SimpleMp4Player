package com.example.kirito.simplemp4player.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.kirito.simplemp4player.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kirito on 2016/9/22.
 */
public class PlayMp4 extends Activity {
    private String path;
    @BindView(R.id.vv)
    VideoView vv;

    private static final String TAG = "PlayMp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.play);

        ButterKnife.bind(this);
        path = getIntent().getStringExtra("path");
        vv.setMediaController(new MediaController(this));
        vv.setVideoURI(Uri.parse(path));
        vv.requestFocus();
        vv.start();
    }
}
