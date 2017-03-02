package com.example.dessusdi.myfirstapp.tools;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dessusdi.myfirstapp.models.air_quality.GlobalObject;
import com.example.dessusdi.myfirstapp.models.search.SearchGlobalObject;
import com.google.gson.Gson;

/**
 * Created by Dimitri on 02/03/2017.
 */

public class WikipediaService {

    private Activity mApplicationContext;

    public WikipediaService(Context context) {
        mApplicationContext = (Activity)context;
    }

    public void fetchCityInformation(String search, final WikipediaService.cityInformationCallback callback) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this.mApplicationContext);

        // Create progress dialog
        final ProgressDialog mDialog = new ProgressDialog(this.mApplicationContext);
        mDialog.setMessage("Loading...");
        mDialog.setCancelable(false);
        mDialog.show();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, RequestBuilder.buildCityInformationURL(search),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mDialog.dismiss();
                        Gson gson = new Gson();
                        //SearchGlobalObject globalSearchObject = gson.fromJson(response, SearchGlobalObject.class);
                        callback.onSuccess("");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void fetchCityImage(String search, final WikipediaService.cityImageCallback callback) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this.mApplicationContext);

        // Create progress dialog
        final ProgressDialog mDialog = new ProgressDialog(this.mApplicationContext);
        mDialog.setMessage("Loading...");
        mDialog.setCancelable(false);
        mDialog.show();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, RequestBuilder.buildCityImageURL(search),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mDialog.dismiss();
                        Gson gson = new Gson();
                        //SearchGlobalObject globalSearchObject = gson.fromJson(response, SearchGlobalObject.class);
                        callback.onSuccess("");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public interface cityInformationCallback {
        void onSuccess(String information);
    }

    public interface cityImageCallback {
        void onSuccess(String information);
    }
}
