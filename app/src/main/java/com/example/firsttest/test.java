package com.example.firsttest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class test {
    public String Api = "AIzaSyBogDyAFl7kirq2hjBeXC2XKsnvPKPB6h0";
    public String Engine = "a1257b69c881e4baa";
    private static final int CONNECTION_TIMEOUT = 1000;
    public String Find() throws IOException {
        String query = "cthulhuwars";
        int page = 2;
        int start = (page - 1) * 10 + 1;
        String url_ = String.format("https://www.googleapis.com/customsearch/v1?key=%s&cx=%s&searchType=image&q=%s&start=%d",Api, Engine, query,start);
        final URL url = new URL(url_);
        final HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setConnectTimeout(CONNECTION_TIMEOUT);
        con.setReadTimeout(CONNECTION_TIMEOUT);

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
}
