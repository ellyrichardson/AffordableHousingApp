package com.example.kustudents.affordablehousingapp._activities;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kustudents.affordablehousingapp.R;
import com.example.kustudents.affordablehousingapp._helpers.SearchFilterTransport;
import com.example.kustudents.affordablehousingapp._interfaces.SFTAsyncResponse;
import com.example.kustudents.affordablehousingapp._models.HousingData;

import java.util.ArrayList;
import java.util.List;

public class ResultsActivity extends AppCompatActivity implements SFTAsyncResponse {

    SearchFilterTransport searchFilterTransport;
    Button heatMapButton;
    static String apiURL = "http://192.168.1.2:8000/api/search/?search=";
    ArrayList<HousingData> housingDataArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        heatMapButton = (Button) findViewById(R.id.resultsHeatMapButton);

        // Gets the searchedFilter object passed from MainActivity
        Intent intent = getIntent();
        String searchFilter = (String)intent.getSerializableExtra("searchedFilter");

        searchFilterTransport = new SearchFilterTransport(this, this, this);

        // Executes the AsyncTask for HTTPUrlConnections with the searchFilter
        searchFilterTransport.execute(apiURL, searchFilter);

        heatMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ArrayList<HousingData> housingDataArrayList = new ArrayList<HousingData>();

                Intent intent = new Intent(ResultsActivity.this, HeatMapActivity.class);
                intent.putParcelableArrayListExtra("housingDataList", housingDataArrayList);
                startActivity(intent);
            }
        });
    }

    // Gets the processed List data from the AsyncTask and assigns it to housingDataArrayList of ResultsActivity
    @Override
    public void returnProcessedList(ArrayList<HousingData> processedList) {
        housingDataArrayList = processedList;
    }
}
