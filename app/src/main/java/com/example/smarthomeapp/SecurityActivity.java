package com.example.smarthomeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;

public class SecurityActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VideoAdapter adapter;
    private List<VideoItem> videoItemList;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view_videos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        videoItemList = loadVideos(); // Implement this method to load your videos
        adapter = new VideoAdapter(this, videoItemList);
        recyclerView.setAdapter(adapter);

        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();

                if (id == R.id.nav_first) {
                    Intent intent = new Intent(SecurityActivity.this, MainActivity.class);
                    startActivity(intent);
                } else if (id == R.id.nav_second) {
                    Intent intent = new Intent(SecurityActivity.this, MapsActivity.class);
                    startActivity(intent);
                } else if (id == R.id.nav_third) {
                    Intent intent = new Intent(SecurityActivity.this, SecurityActivity.class);
                    startActivity(intent);                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private List<VideoItem> loadVideos() {
        // Load your videos here (e.g., from a local source or a server)
        // This is just a placeholder implementation
        // TODO Add Database for video storage.
        List<VideoItem> videoList = new ArrayList<>();
        videoList.add(new VideoItem("Sample Video 1", "http://example.com/video1.mp4"));
        videoList.add(new VideoItem("Sample Video 2", "http://example.com/video2.mp4"));
        // Add more videos as needed
        return videoList;
    }
}
