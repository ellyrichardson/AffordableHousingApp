package com.example.kustudents.affordablehousingapp._models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;

public class HousingData implements Parcelable{
    private int inspectionScore;
    private String housingCity;
    private String housingState;
    private String housingZip;
    private String housingDevelopmentName;
    private String housingLat;
    private String housingLong;

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

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest,int arg1) {
        // TODO Auto-generated method stub
        dest.writeInt(inspectionScore);
        dest.writeString(housingCity);
        dest.writeString(housingState);
        dest.writeString(housingZip);
        dest.writeString(housingDevelopmentName);
        dest.writeString(housingLat);
        dest.writeString(housingLong);
    }

    public HousingData(Parcel in) {
        inspectionScore = 0;
        housingCity = "";
        housingState = "";
        housingZip = "";
        housingDevelopmentName = "";
        housingLat = "";
        housingLong = "";
    }

    public static final Parcelable.Creator<HousingData> CREATOR = new Parcelable.Creator<HousingData>()
    {
        public HousingData createFromParcel(Parcel in)
        {
            return new HousingData(in);
        }

        @Override
        public HousingData[] newArray(int size) {
            return new HousingData[size];
        }
    };
}
