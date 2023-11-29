package com.example.smarthomeapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    //for humidity sensor
    TextView displayHum;
    private SensorManager mSensorManager;

    SensorEventListener listener = new SensorEventListener() {

        public void onSensorChanged(SensorEvent event) {
            float humidity = event.values[0];
            displayHum.setText(String.valueOf(humidity)+"%");
        }
        public void onAccuracyChanged(Sensor sensor, int accuracy){
        }

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                } else if (id == R.id.nav_second) {
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(intent);
                } else if (id == R.id.nav_third) {
                    Intent intent = new Intent(MainActivity.this, SecurityActivity.class);
                    startActivity(intent);                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        //Humidity Sensor
        displayHum = (TextView)findViewById(R.id.displayHumidity);
        displayHum.setVisibility(View.GONE);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> mList= mSensorManager.getSensorList(Sensor.TYPE_ALL);

        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        displayHum.setVisibility(View.VISIBLE);
        mSensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        //check if sensor working
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) == null){
            displayHum.setText("Sensor not Available");
        }


    }
    public void onDeviceActionButtonClicked(View view) {
        // This method is called when the fake button is clicked.
        TextView statusTextView = findViewById(R.id.textview_device_status);

        // Toggle device status for the sake of example
        if (statusTextView.getText().toString().contains("Disconnected")) {
            // Change status to 'Connected' and perform necessary operations
            statusTextView.setText("Device Status: Connected");
            // You can add more logic to handle the device connection here
        } else {
            // Change status to 'Disconnected' and perform necessary operations
            statusTextView.setText("Device Status: Disconnected");
            // You can add more logic to handle the device disconnection here
        }
    }

}
