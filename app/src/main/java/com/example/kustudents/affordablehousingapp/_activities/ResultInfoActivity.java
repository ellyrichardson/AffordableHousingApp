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

        developmentName = (TextView) findViewById(R.id.resInfoActHousingDevNameTextView);
        inspScore = (TextView) findViewById(R.id.resInfoActInspScoreTxtView);
        city = (TextView) findViewById(R.id.resInfoActHousingCityTextView);
        state = (TextView) findViewById(R.id.resInfoActHousingStateTextView);
        zip = (TextView) findViewById(R.id.resInfoActHousingZipTextView);

        developmentName.setText((String)intent.getSerializableExtra("developmentName"));
        inspScore.setText((String)intent.getSerializableExtra("inspectionScore"));
        city.setText((String)intent.getSerializableExtra("city"));
        state.setText((String)intent.getSerializableExtra("state"));
        //zip.setText("Housing Zip: " + (String)intent.getSerializableExtra("zip"));

    }
}
