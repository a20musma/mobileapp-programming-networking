package com.example.networking;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


import com.google.gson.Gson;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private Mountain[] mountains;
    private WebView webView;

    private ListView listView;

    private final String JSON_URL = "HTTPS_URL_TO_JSON_DATA_CHANGE_THIS_URL";
    private final String JSON_FILE = "mountains.json";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new JsonFile(this, this).execute(JSON_FILE);


    }

    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity ==>", json);

        Gson gson = new Gson();
        mountains = gson.fromJson(json,Mountain[].class);

        ArrayAdapter<Mountain> arrayAdapter = new ArrayAdapter<>(this, R.layout.listview_item, R.id.item, mountains);

        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(arrayAdapter);

        for (int i = 0; i < mountains.length; i++) {
            Log.d("MainActivity ==>","Hittade ett berg" +  mountains[i].getName() + " " + mountains[i].getAuxdata().getWiki());


        }





    }






}
