package com.example.videostreamrestapi.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.videostreamrestapi.PlayerActivity;
import com.example.videostreamrestapi.R;
import com.example.videostreamrestapi.models.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/*2: extend the class vy RecyclerView.....*/
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>{

    /*3: Add these and create constructor*/
    ArrayList<Video> list;
    Context context;
    private static final String TAG = "VideoAdapter";

    public VideoAdapter(ArrayList<Video> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {

//        holder.videoThumbnail.setImageResource(video.getThumbnail());
        Video video = list.get(position);

        Log.d(TAG, "onBindViewHolder: "+ video.getThumbnail());


        holder.title.setText(video.getTitle());
        holder.source.setText(video.getSource());
        holder.uploaded.setText(video.getUploaded());
        holder.views.setText(video.getViews());
//        Glide.with(context)
//                .asBitmap()
//                .load(video.getThumbnail())
//                .into(holder.videoThumbnail);
        Picasso.get().load(video.getThumbnail()).fit().placeholder(R.drawable.load).into(holder.videoThumbnail);
        holder.vv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putSerializable("videoData", list.get(position));
                Intent i = new Intent(context, PlayerActivity.class);
                i.putExtras(b);
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /*1st: create viewholder that extends RecyclerView.ViewHolder*/
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView videoThumbnail;
        TextView title, source, uploaded, views;
        View vv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            videoThumbnail = itemView.findViewById(R.id.videoThumbnail);
            title= itemView.findViewById(R.id.title);
            source = itemView.findViewById(R.id.source);
            uploaded = itemView.findViewById(R.id.uploaded);
            views = itemView.findViewById(R.id.views);
            vv = itemView;

        }
    }
}
