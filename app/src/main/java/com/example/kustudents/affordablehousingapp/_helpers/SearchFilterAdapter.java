package com.example.kustudents.affordablehousingapp._helpers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kustudents.affordablehousingapp.R;
import com.example.kustudents.affordablehousingapp._models.HousingData;

import java.util.List;

public class SearchFilterAdapter extends  RecyclerView.Adapter<SearchFilterAdapter.CustomViewHolder>{
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
        holder.dDevelopmentName.setText(housingData.getHousingDevelopmentName());
        holder.dCity.setText(housingData.getHousingCity());
        holder.dState.setText(housingData.getHousingState());
        holder.dCounty.setText(housingData.getHousingState());
        holder.dInspScore.setText(housingData.getInspectionScore());
    }

    @Override
    public int getItemCount() {
        return housingDataList.size();
    }
}
