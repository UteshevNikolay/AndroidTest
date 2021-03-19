package com.example.androidtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    private String jsonURL = "https://www.cbr-xml-daily.ru/daily_json.js";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        loadJSONFormURL(jsonURL);

    }

    private void loadJSONFormURL(String jsonURL) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET,jsonURL,
        new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject object = new JSONObject(response);
                    JSONObject jsonValute = object.getJSONObject("Valute");
                    Iterator<?> iter = jsonValute.keys();
                    ArrayList<JSONObject> listItems = new ArrayList<>();
                    while (iter.hasNext()){
                        JSONObject jo = jsonValute.optJSONObject(iter.next().toString());
                       listItems.add(jo);

                    }
                    ListAdapter adapter = new ValuteAdapter(getApplicationContext(),R.layout.row,R.id.textViewID,listItems);
                    listView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
        new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}