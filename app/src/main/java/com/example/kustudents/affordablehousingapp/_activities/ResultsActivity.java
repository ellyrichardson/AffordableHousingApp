package com.example.kustudents.affordablehousingapp._activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kustudents.affordablehousingapp.R;
import com.example.kustudents.affordablehousingapp._helpers.SearchFilterTransport;

public class ResultsActivity extends AppCompatActivity {

    SearchFilterTransport searchFilterTransport;
    Button heatMapButton;
    static String apiURL = "http://192.168.1.2:8000/api/search/?search=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        heatMapButton = (Button) findViewById(R.id.resultsHeatMapButton);

        // Gets the searchedFilter object passed from MainActivity
        Intent intent = getIntent();
        String searchFilter = (String)intent.getSerializableExtra("searchedFilter");

        searchFilterTransport = new SearchFilterTransport(this, this);

        // Executes the AsyncTask for HTTPUrlConnections with the searchFilter
        searchFilterTransport.execute(apiURL, searchFilter);

        heatMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ResultsActivity.this, HeatMapActivity.class);
                startActivity(intent);
            }
        });
    }
}
