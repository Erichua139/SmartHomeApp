package com.example.smarthomeapp;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class SensorsActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    //for humidity sensor
    TextView displayHum;
    private SensorManager mSensorManager;

    //access virtual sensor
    SensorEventListener listener = new SensorEventListener() {

        //update sensor display when change in virtual sensor detected
        public void onSensorChanged(SensorEvent event) {
            float humidity = event.values[0];
            displayHum.setText(String.valueOf(humidity)+"%"); //display in activity
        }
        public void onAccuracyChanged(Sensor sensor, int accuracy){
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

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
                    Intent intent = new Intent(SensorsActivity.this, MainActivity.class);
                    startActivity(intent);
                } else if (id == R.id.nav_second) {
                    Intent intent = new Intent(SensorsActivity.this, MapsActivity.class);
                    startActivity(intent);
                } else if (id == R.id.nav_third) {
                    Intent intent = new Intent(SensorsActivity.this, SecurityActivity.class);
                    startActivity(intent);
                } else if (id == R.id.nav_fourth) {
                    Intent intent = new Intent(SensorsActivity.this, SensorsActivity.class);
                    startActivity(intent);
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        //Humidity Sensor Implementation
        displayHum = (TextView)findViewById(R.id.displayHumidity);
        displayHum.setVisibility(View.GONE);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> mList= mSensorManager.getSensorList(Sensor.TYPE_ALL);

        //listen to sensor
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        displayHum.setVisibility(View.VISIBLE);
        mSensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        
        //check if sensor working
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) == null){
            displayHum.setText("Sensor not Available");
        }


    }
}
