package com.example.kustudents.affordablehousingapp._models;

public class HousingData {
    int inspectionScore;
    String housingCity;
    String housingState;
    String housingZip;
    String housingDevelopmentName;
    String housingLat;
    String housingLong;

    // sets values for the posts
    public HousingData(int p_inspectionScore, String p_housingCity, String p_housingState, String p_housingZip, String p_housingDevelopmentName,
                       String p_housingLat, String p_housingLong) {
        this.inspectionScore = p_inspectionScore;
        this.housingCity = p_housingCity;
        this.housingState = p_housingState;
        this.housingZip = p_housingZip;
        this.housingDevelopmentName = p_housingDevelopmentName;
        this.housingLat = p_housingLat;
        this.housingLong = p_housingLong;
    }

    public int getInspectionScore() {
        return this.inspectionScore;
    }

    public String getHousingCity() {
        return this.housingCity;
    }

    public String getHousingState() {
        return this.housingState;
    }

    public String getHousingZip() {
        return this.housingZip;
    }

    public String getHousingDevelopmentName() {
        return this.housingDevelopmentName;
    }

    public String getHousingLat() {
        return this.housingLat;
    }

    public String getHousingLong() {
        return this.housingLong;
    }
}
