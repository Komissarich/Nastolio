package com.example.firsttest;

import static android.util.Log.i;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public boolean go = true;
    public String Api = "AIzaSyBogDyAFl7kirq2hjBeXC2XKsnvPKPB6h0";
    public String Engine = "a1257b69c881e4baa";
    private static final int CONNECTION_TIMEOUT = 1000;
    private final static String FILE_NAME = "content.txt";

    public String Find() throws IOException {

        String query = "cthulhuwars";
        int page = 2;
        int start = (page - 1) * 10 + 1;
        @SuppressLint("DefaultLocale") String url_ = String.format("https://www.googleapis.com/customsearch/v1?key=%s&cx=%s&searchType=image&q=%s&start=%d", Api, Engine, query, start);
        final URL url = new URL(url_);
        final HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setConnectTimeout(CONNECTION_TIMEOUT);
        con.setReadTimeout(CONNECTION_TIMEOUT);
        Log.d("2222", "DONE");
        Log.d("2222", String.valueOf(con.getInputStream()));
        try (final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            final StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
                content.append(System.lineSeparator());
            }
            System.out.println(content.getClass());
            return content.toString();
        } catch (final Exception ex) {
            ex.printStackTrace();
            return "";
        }

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        test a = new test();
        setContentView(R.layout.activity_main);
        try {
             Log.d("111111", Find());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}