package com.example.smarthomeapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private final Context context;
    private final List<VideoItem> videoItems;

    public VideoAdapter(Context context, List<VideoItem> videoItems) {
        this.context = context;
        this.videoItems = videoItems;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.video_item, parent, false);
        return new VideoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        VideoItem videoItem = videoItems.get(position);

        // Set click listener for the play button
        holder.playButton.setOnClickListener(v -> {
            // Construct the Uri for the video resource
            Uri videoUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + videoItem.getVideoResId());

            // Create an intent to launch FullScreenVideoActivity
            Intent fullScreenIntent = new Intent(context, FullScreenVideoActivity.class);
            fullScreenIntent.setData(videoUri);
            context.startActivity(fullScreenIntent);
        });
    }

    @Override
    public int getItemCount() {
        return videoItems.size();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        VideoView videoView;
        ImageView playButton;

        VideoViewHolder(View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.video_view);
            playButton = itemView.findViewById(R.id.play_button);
        }
    }
}
