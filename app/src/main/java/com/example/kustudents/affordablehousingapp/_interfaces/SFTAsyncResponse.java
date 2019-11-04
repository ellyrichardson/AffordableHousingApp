package com.example.kustudents.affordablehousingapp._interfaces;

import com.example.kustudents.affordablehousingapp._models.HousingData;

import java.util.ArrayList;

public interface SFTAsyncResponse {
    void returnProcessedList(ArrayList<HousingData> processedList);
}
