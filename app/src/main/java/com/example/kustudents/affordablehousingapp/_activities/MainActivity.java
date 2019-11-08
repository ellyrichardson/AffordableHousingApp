package com.example.kustudents.affordablehousingapp._activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kustudents.affordablehousingapp.R;
import com.example.kustudents.affordablehousingapp._helpers.SearchFilterTransport;
import com.example.kustudents.affordablehousingapp._interfaces.SFTAsyncResponse;
import com.example.kustudents.affordablehousingapp._models.HousingData;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable, SFTAsyncResponse {

    Button searchFilterBtn;
    EditText searchAttributeEditTxt, searchCityEditText, searchStateEditText, searchZipEditText, searchInspScoreEditText;
    SearchFilterTransport searchFilterTransport;

    static String apiURL = "http://192.168.1.2:8000/api/search/?search=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchFilterBtn = (Button) findViewById(R.id.searchFilterButton);
        searchAttributeEditTxt = (EditText) findViewById(R.id.searchAttributeEditText);
        searchCityEditText = (EditText) findViewById(R.id.searchCityEditText);
        searchZipEditText = (EditText) findViewById(R.id.searchZipCodeEditText);
        searchStateEditText = (EditText) findViewById(R.id.searchStateEditText);
        searchInspScoreEditText = (EditText) findViewById(R.id.searchInspNumEditText);

        searchFilterTransport = new SearchFilterTransport(this, this, this);

        searchFilterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchFilter = searchAttributeEditTxt.getText().toString();
                String cityFilter = searchCityEditText.getText().toString();
                String zipEditFilter = searchZipEditText.getText().toString();
                String stateFilter = searchStateEditText.getText().toString();
                String inspScoreFilter = searchInspScoreEditText.getText().toString();

                // Passes the searchFilter variable to the ResultsActivity when ResultsActivity starts
                Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
                intent.putExtra("searchedFilter", searchFilter);
                startActivity(intent);
            }
        });
    }

    private String concatenateAPIParams(String devName, String city, String state, String county, String inspScore) {
        return devName + "," + city + "," + state + "," + county + "," + inspScore;
    }

    @Override
    public void returnProcessedList(ArrayList<HousingData> processedList) {

    }
}
