package com.example.kustudents.affordablehousingapp._activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.kustudents.affordablehousingapp.R;

import org.w3c.dom.Text;

public class ResultInfoActivity extends AppCompatActivity {

    TextView developmentName, inspScore, city, state, zip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_info);

        Intent intent = getIntent();

        developmentName = (TextView) findViewById(R.id.resInfoHousingDevNameTextView);
        inspScore = (TextView) findViewById(R.id.resInfoInspScoreTxtView);
        city = (TextView) findViewById(R.id.resInfoHousingCityTextView);
        state = (TextView) findViewById(R.id.resInfoHousingStateTextView);
        zip = (TextView) findViewById(R.id.resInfoHousingZipTextView);

        developmentName.setText("Housing Development Name: " + (String)intent.getSerializableExtra("developmentName"));
        inspScore.setText("Inspection Score: " + (String)intent.getSerializableExtra("inspectionScore"));
        city.setText("Housing City: " + (String)intent.getSerializableExtra("city"));
        state.setText("Housing State: " + (String)intent.getSerializableExtra("state"));
        zip.setText("Housing Zip: " + (String)intent.getSerializableExtra("zip"));

    }
}
