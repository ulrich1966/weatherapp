package de.auli.weatherapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import de.auli.weatherapp.model.Result;

class WeatherServerReq {
    private static final String TAG = WeatherServerReq.class.getSimpleName();
    private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=%1$s&lang=de&appid=%2$s";
    //private static final String KEY = String.format("%s", R.string.weather_key); --> Results in different Number!!!
    private static final String KEY = "40c5de3e98b083a9edc44d6a653de166";

    public static Result getWeather(String city) throws JSONException, IOException {
        String jsonData = getFromServer(String.format(URL, city, KEY));
        UnmarshallService us = new UnmarshallService();
        try {
            Result result = us.unmarshall(jsonData);
            result = addImage(result);
            return result;
        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
        return null;
    }

    private static String getFromServer(String url) throws IOException {
        StringBuilder sb = new StringBuilder();
        URL _url = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) _url.openConnection();
        final int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            try {
                bufferedReader.close();
            } catch (IOException e) {
                Log.e(TAG, "getFromServer()", e);
            }
        }
        httpURLConnection.disconnect();
        String jsonResult = sb.toString();
        Log.d(TAG, jsonResult);
        return jsonResult;
    }

    private static Result addImage(Result result) throws IOException {
        String icon = result.getWeather().get(0).getIcon();
        String url$ = String.format("http://openweathermap.org/img/w/%s.png", icon);
        Log.d(TAG, String.format("Request goes to: %s", url$));
        URL req = new URL(url$);
        HttpURLConnection c = (HttpURLConnection) req.openConnection();
        Bitmap bmp = BitmapFactory.decodeStream(c.getInputStream());
        c.disconnect();
        result.setBmp(bmp);
        return result;
    }
}
