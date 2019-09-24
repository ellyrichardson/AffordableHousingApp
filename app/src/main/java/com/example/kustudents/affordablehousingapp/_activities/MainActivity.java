package com.example.kustudents.affordablehousingapp._activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kustudents.affordablehousingapp.R;

public class MainActivity extends AppCompatActivity {

    Button searchFilterBtn;
    EditText searchAttributeEditTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchFilterBtn = (Button) findViewById(R.id.searchFilterButton);
        searchAttributeEditTxt = (EditText) findViewById(R.id.searchAttributeEditText);

        searchFilterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchFilter = searchAttributeEditTxt.getText().toString();

            }
        });
    }
}
