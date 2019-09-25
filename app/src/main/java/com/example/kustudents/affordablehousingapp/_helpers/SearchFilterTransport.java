package com.example.kustudents.affordablehousingapp._helpers;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.kustudents.affordablehousingapp._models.HousingData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SearchFilterTransport extends AsyncTask<String, String, String> {

    private WeakReference<Context> searchFilterInformation;
    private Activity activity;
    private SearchFilterAdapter searchFilterAdapter;

    public SearchFilterTransport(Context context, Activity activity) {
        searchFilterInformation = new WeakReference<>(context);
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... params) {

        StringBuilder result = new StringBuilder();

        HttpURLConnection httpURLConnection = null;

        try {
            // Sets up connection to the URL, which is params[0]
            httpURLConnection = (HttpURLConnection) new URL(params[0]).openConnection();
            // Sets the request method for the URL
            httpURLConnection.setRequestMethod("GET");

            // Tells the URL that I want to read the response data
            httpURLConnection.setDoInput(true);

            // // Representing the input stream to URL response
            InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            // reading the input stream / response from the url
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Disconnects socket after using
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }

        Log.e("TAG", result.toString());
        return result.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        // Expecting a stringed JSON from the REST API
        Log.e("TAG", result);

        // retrieves the context passed
        Context context = searchFilterInformation.get();

        try {
            if (context != null) {
                parseJSONFromAPI(result);
            }
        } catch (JSONException e) {
            Log.d("Json","Exception = "+e.toString());
        }
    }

    public void parseJSONFromAPI(String json) throws JSONException {
        // List for the HousingData objects
        List<HousingData> housingDataList = new ArrayList<>();

        // Adapter to output the housingDataList to the results page
        searchFilterAdapter = new SearchFilterAdapter(housingDataList);

        // Gets the base JSON data
        JSONObject responseJSON = new JSONObject(json);
        // Gets the array of results from the base JSON data
        JSONArray jsonArray = responseJSON.getJSONArray("results");

        for (int i = 0; i < jsonArray.length(); i++) {
            // Isolates JSON objects from the array based on their index
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            // Each JSON object from the array of results will be turned into a new HousingData object
            housingDataList.add(new HousingData(jsonObject.getInt("inspection_score"), jsonObject.getString("city"),
                    jsonObject.getString("state_code"), jsonObject.getString("zip"), jsonObject.getString("development_name"),
                    jsonObject.getString("latitude"), jsonObject.getString("longitude")));
        }

        // Notifies the adapter when housingDataList gets updated so that the Results recyclerview will be updated by the adapter.
        searchFilterAdapter.notifyDataSetChanged();
    }
}
