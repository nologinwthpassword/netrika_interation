package ru.rtlabs;

import java.io.*;
import java.net.*;
import java.util.LinkedHashMap;
import java.util.Map;


public class Cas {

    public String getHTML(String urlToRead) {
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        String result = "";
        try {
            url = new URL(urlToRead);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = rd.readLine()) != null) {
                result += line;
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(java.util.Calendar.getInstance().getTime() + " " +result);
        return result;
    }



    }

