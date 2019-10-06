package com.example.kustudents.affordablehousingapp._activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kustudents.affordablehousingapp.R;
import com.example.kustudents.affordablehousingapp._helpers.SearchFilterTransport;

public class ResultsActivity extends AppCompatActivity {

    SearchFilterTransport searchFilterTransport;
    static String apiURL = "http://192.168.1.2:8000/api/search/?search=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Gets the searchedFilter object passed from MainActivity
        Intent intent = getIntent();
        String searchFilter = (String)intent.getSerializableExtra("searchedFilter");

        searchFilterTransport = new SearchFilterTransport(this, this);

        // Executes the AsyncTask for HTTPUrlConnections with the searchFilter
        searchFilterTransport.execute(apiURL, searchFilter);
    }
}
