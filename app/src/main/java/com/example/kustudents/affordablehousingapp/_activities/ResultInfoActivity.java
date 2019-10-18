package com.example.kustudents.affordablehousingapp._activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.kustudents.affordablehousingapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

public class ResultInfoActivity extends AppCompatActivity implements OnMapReadyCallback {

    TextView developmentName, inspScore, city, state, zip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_info);

        Intent intent = getIntent();

        developmentName = (TextView) findViewById(R.id.resInfoActHousingDevNameTextView);
        inspScore = (TextView) findViewById(R.id.resInfoActInspScoreTxtView);
        city = (TextView) findViewById(R.id.resInfoActHousingCityTextView);
        state = (TextView) findViewById(R.id.resInfoActHousingStateTextView);
        zip = (TextView) findViewById(R.id.resInfoActHousingZipTextView);

        developmentName.setText((String)intent.getSerializableExtra("developmentName"));
        inspScore.setText((String)intent.getSerializableExtra("inspectionScore"));
        city.setText((String)intent.getSerializableExtra("city"));
        state.setText((String)intent.getSerializableExtra("state"));
        zip.setText("66222");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.resultsHeatMapFragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // This void function has to be overriden under OnMapReadyCallBack so that getMapAsync() can work in this activity.
        LatLng housingMarker = new LatLng(getHousingLat(), getHousingLong());
        googleMap.addMarker(new MarkerOptions().position(housingMarker)
                .title(getHousingDevelopmentName()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(housingMarker, 14.0f));
    }

    public float getHousingLat() {
        Intent intent = getIntent();
        return Float.parseFloat((String)intent.getSerializableExtra("latitude"));
    }

    public float getHousingLong() {
        Intent intent = getIntent();
        return Float.parseFloat((String)intent.getSerializableExtra("longitude"));
    }

    public String getHousingDevelopmentName() {
        Intent intent = getIntent();
        return (String)intent.getSerializableExtra("developmentName");
    }
}
