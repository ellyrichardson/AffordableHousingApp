package com.example.kustudents.affordablehousingapp._activities;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.kustudents.affordablehousingapp.R;
import com.example.kustudents.affordablehousingapp._fragments.HeatMapFragment;
import com.example.kustudents.affordablehousingapp._models.HousingData;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class HeatMapActivity extends FragmentActivity implements OnMapReadyCallback {

    ArrayList<HousingData> housingDataArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heat_map);

        // Gets the passed housing Data List from the Results Activity
        housingDataArrayList = (ArrayList<HousingData>) getIntent().getSerializableExtra("housingDataList");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.hmHeatMapFragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // This void function has to be overriden under OnMapReadyCallBack so that getMapAsync() can work in this activity.
        for (HousingData value : housingDataArrayList) {
            LatLng housingLocation = new LatLng(Float.parseFloat(value.getHousingLat()), Float.parseFloat(value.getHousingLong()));
            googleMap.addMarker(new MarkerOptions().position(housingLocation)
                    .title(value.getHousingDevelopmentName()));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(housingLocation));
        }
    }
}
