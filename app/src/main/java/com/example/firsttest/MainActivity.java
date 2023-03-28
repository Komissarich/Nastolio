package com.example.http;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public boolean go = true;
    public String Api = "AIzaSyBogDyAFl7kirq2hjBeXC2XKsnvPKPB6h0";
    public String Engine = "a1257b69c881e4baa";
    private static final int CONNECTION_TIMEOUT = 1000;
    private final static String FILE_NAME = "content.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadDogImage();
    }

    private void loadDogImage() {

        // getting a new volley request
        // queue for making new requests
        RequestQueue volleyQueue = Volley.newRequestQueue(this);
        String query = "cthulhuwars";
        int page = 2;
        int start = (page - 1) * 10 + 1;
        String url_ = String.format("https://www.googleapis.com/customsearch/v1?key=%s&cx=%s&searchType=image&q=%s&start=%d", Api, Engine, query, start);
        URL url = null;
        Log.d("EEEEE", url_);

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(url_, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (null != response) {
                            String filename = "file.txt";
                            String outputString = "Hello world!";

                            try {
                                FileOutputStream outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                                outputStream.write(outputString.getBytes());
                                outputStream.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            try {
                                FileInputStream inputStream = openFileInput(filename);
                                BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
                                StringBuilder total = new StringBuilder();
                                String line;
                                while ((line = r.readLine()) != null) {
                                    total.append(line);
                                }
                                r.close();
                                inputStream.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            System.out.println(response.toString());

                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }
    private void writeToFile(String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
