package com.example.smarthomeapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class FullScreenVideoActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_video);

        videoView = findViewById(R.id.fullscreen_video_view);

        // Get the video URI from the intent
        Uri videoUri = getIntent().getData();
        videoView.setVideoURI(videoUri);
        videoView.start();
    }

    @Override
    public void onBackPressed() {
        // If you want to do any other cleanup, do it here
        super.onBackPressed();
    }
}
