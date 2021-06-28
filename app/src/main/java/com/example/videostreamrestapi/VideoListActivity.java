package com.example.videostreamrestapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.videostreamrestapi.adapters.VideoAdapter;
import com.example.videostreamrestapi.models.Video;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class VideoListActivity extends AppCompatActivity {
    private static final String TAG = "VideoListActivity";
    RecyclerView videoList;
    ArrayList<Video> list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        videoList = findViewById(R.id.videoList);
        list = new ArrayList<>();
        initRecyclerView();
        //enterFakeResources();
        getJsonData();
    }

    private void getJsonData() {
        String URL = "https://raw.githubusercontent.com/bikashthapa01/myvideos-android-app/master/data.json";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Log.d(TAG, "onResponse: " + response);
                try {
                    JSONArray categories = response.getJSONArray("categories");
                    JSONObject categoriesData = categories.getJSONObject(0);
                    JSONArray videos = categoriesData.getJSONArray("videos");

                    Log.d(TAG, "onResponse: " + videos);
                    for(int i = 0; i <videos.length();i++){
                        JSONObject video = videos.getJSONObject(i);
                        String imageUrl = video.getString("thumb");
//                        Log.d(TAG, "onResponse: "+video.getString("description"));

                        //Log.d(TAG, "onResponse: "+ video.getString("title"));
                        JSONArray videoUrl = video.getJSONArray("sources");
                        Video v = new Video(video.getString("title"),
                                video.getString("subtitle"),
                                "10 days ago",
                                "100K Views",
                                imageUrl,
                                videoUrl.getString(0));
                        v.setDescription(video.getString("description"));
                        Log.d(TAG, "onResponse: parsing json "+v.toString());
                        list.add(v);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error.getMessage());
            }
        });

        requestQueue.add(objectRequest);
    }

//    public void enterFakeResources() {
//        for (int i = 0; i < 100; i++) {
//            list.add(new Video("Video #" + i, "Google", "10 days ago", "10000", R.drawable.sumu, "http://www.google.com/"));
//        }
//    }

    public void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        videoList.setLayoutManager(linearLayoutManager);
        VideoAdapter adapter = new VideoAdapter(list, this);
        videoList.setAdapter(adapter);
    }
}