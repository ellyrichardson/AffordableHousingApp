
package com.example.kustudents.affordablehousingapp._helpers;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.kustudents.affordablehousingapp.R;
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


public class SearchFilterTransport extends AsyncTask<String, String, String> {

    private WeakReference<Context> searchFilterInformation;
    private Activity activity;
    private SearchFilterAdapter searchFilterAdapter;

    HttpURLConnection httpURLConnection;

    private RecyclerView resultsRecyclerView;

    public SearchFilterTransport(Context context, Activity activity) {
        searchFilterInformation = new WeakReference<>(context);
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... params) {

        StringBuilder result = new StringBuilder();

        try {
            // Sets up connection to the URL, which is params[0]
            httpURLConnection = (HttpURLConnection) new URL(params[0] + params[1]).openConnection();
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

        // List for the HousingData objects
        ArrayList<HousingData> housingDataList = new ArrayList<>();

        try {
            if (context != null) {

                // Adapter to output the housingDataList to the results page
                searchFilterAdapter = new SearchFilterAdapter(housingDataList);

                RecyclerView.LayoutManager rLayoutManager = new LinearLayoutManager(context.getApplicationContext());
                resultsRecyclerView = (RecyclerView) activity.findViewById(R.id.resultsRecyclerView);
                resultsRecyclerView.setLayoutManager(rLayoutManager);
                resultsRecyclerView.setItemAnimator(new DefaultItemAnimator());
                // RecyclerView uses the adapter
                resultsRecyclerView.setAdapter(searchFilterAdapter);


                // Gets the base JSON data
                JSONObject responseJSON = new JSONObject(result);
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
        } catch (JSONException e) {
            Log.d("Json","Exception = "+e.toString());
        }
    }
}
