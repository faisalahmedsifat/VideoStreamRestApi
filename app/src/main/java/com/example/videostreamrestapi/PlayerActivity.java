package com.example.videostreamrestapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.videostreamrestapi.models.Video;

import java.net.URI;

public class PlayerActivity extends AppCompatActivity {
    private TextView title, description;
    private ProgressBar progressBar;
    private static final String TAG = "PlayerActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = findViewById(R.id.progressBar);

        Intent i = getIntent();
        Bundle data = i.getExtras();
        Video v = (Video) data.getSerializable("videoData");
        getSupportActionBar().setTitle(v.getTitle());
        Log.d(TAG, "onCreate: " + v.toString());
        title = findViewById(R.id.videoTitle);
        title.setText(v.getTitle());
        description = findViewById(R.id.videoDescription);
        description.setText(v.getDescription());

        VideoView videoPlayer = findViewById(R.id.videoView);
        Uri videoUrl = Uri.parse(v.getURL());
        videoPlayer.setVideoURI(videoUrl);

        MediaController mc = new MediaController(this);
        videoPlayer.setMediaController(mc);

        videoPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoPlayer.start();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}