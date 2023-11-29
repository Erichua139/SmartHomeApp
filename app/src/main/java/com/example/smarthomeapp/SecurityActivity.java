package com.example.smarthomeapp;

import android.content.Intent;
import android.net.Uri;
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
        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recycler_view_videos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Initialize the list of VideoItems
        List<VideoItem> videoItems = new ArrayList<>();
        videoItems.add(new VideoItem(R.raw.db_01_01_23));
        // Add more video items as needed

        // Initialize the adapter and set it to the RecyclerView
        adapter = new VideoAdapter(this, videoItems);
        recyclerView.setAdapter(adapter);



        //Code for menu
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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
                    startActivity(intent);
                } else if (id == R.id.nav_fourth) {
                    Intent intent = new Intent(SecurityActivity.this, SensorsActivity.class);
                    startActivity(intent);
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
}
