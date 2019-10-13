package com.example.kustudents.affordablehousingapp._helpers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kustudents.affordablehousingapp.R;
import com.example.kustudents.affordablehousingapp._activities.MainActivity;
import com.example.kustudents.affordablehousingapp._activities.ResultInfoActivity;
import com.example.kustudents.affordablehousingapp._activities.ResultsActivity;
import com.example.kustudents.affordablehousingapp._models.HousingData;

import java.util.List;

public class SearchFilterAdapter extends  RecyclerView.Adapter<SearchFilterAdapter.CustomViewHolder> {
    private List<HousingData> housingDataList;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView dDevelopmentName, dCity, dState, dCounty, dInspScore;

        public CustomViewHolder(View view) {
            super(view);
            dDevelopmentName = (TextView) view.findViewById(R.id.resultsDevNameTextView);
            dCity = (TextView) view.findViewById(R.id.resultsCityTextView);
            dState = (TextView) view.findViewById(R.id.resultsStateTextView);
            dCounty = (TextView) view.findViewById(R.id.resultsCountyTextView);
            dInspScore = (TextView) view.findViewById(R.id.resultsInspScoreTextView);
        }
    }

    public SearchFilterAdapter(List<HousingData> housingDataList){
        this.housingDataList = housingDataList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_row, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        HousingData housingData = housingDataList.get(position);

        final String housingDevelopmentName = housingData.getHousingDevelopmentName();
        final String housingCity = housingData.getHousingCity();
        final String housingState = housingData.getHousingState();
        final String housingCounty = housingData.getHousingState();
        final String housingInspScore = Integer.toString(housingData.getInspectionScore());
        final String housingLat = housingData.getHousingLat();
        final String housingLong = housingData.getHousingLong();

        holder.dDevelopmentName.setText(housingDevelopmentName);
        holder.dCity.setText(housingCity);
        holder.dState.setText(housingState);
        holder.dCounty.setText(housingCounty);
        holder.dInspScore.setText(housingInspScore);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ResultInfoActivity.class);
                intent.putExtra("developmentName", housingDevelopmentName);
                intent.putExtra("city", housingCity);
                intent.putExtra("state", housingState);
                intent.putExtra("county", housingCounty);
                intent.putExtra("inspectionScore", housingInspScore);
                intent.putExtra("latitude", housingLat);
                intent.putExtra("longitude", housingLong);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return housingDataList.size();
    }
}