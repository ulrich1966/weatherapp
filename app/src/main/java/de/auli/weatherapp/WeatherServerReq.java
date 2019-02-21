package de.auli.weatherapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONException;

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
    private static HttpURLConnection connection = null;

    public static Result getWeather(String city, boolean isBmp) throws JSONException, IOException {
        Result result = crateResult(city);
        if(isBmp){
            addImage(result);
        }
        return result;
    }

    public static Result getWeather(String city) throws JSONException, IOException {
        return getWeather(city, true);
    }

    private static Result crateResult(String city) throws JSONException, IOException {
        String jsonData = makeServerRequest(String.format(URL, city, KEY));
        UnmarshallService us = new UnmarshallService();
        Result result = null;
        try {
            result = us.unmarshall(jsonData);
        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
        return result;
    }

    private static String makeServerRequest(String url) throws IOException {
        StringBuilder sb = new StringBuilder();
        WeatherServerReq.connection = (HttpURLConnection) new URL(url).openConnection();
        WeatherServerReq.connection.setRequestProperty("Content-Type", "application/json");

        if (WeatherServerReq.connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            InputStreamReader inputStreamReader = new InputStreamReader(WeatherServerReq.connection.getInputStream());
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
        WeatherServerReq.connection.disconnect();
        String jsonResult = sb.toString();
        return jsonResult;
    }

    private static boolean addImage(Result result) throws IOException {
        boolean success = false;
        String icon = result.getWeather().get(0).getIcon();
        String url$ = String.format("http://openweathermap.org/img/w/%s.png", icon);
        Log.d(TAG, String.format("Request goes to: %s", url$));
        URL reqUrl = new URL(url$);
        WeatherServerReq.connection = (HttpURLConnection) reqUrl.openConnection();
        if (WeatherServerReq.connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            Bitmap bmp = BitmapFactory.decodeStream(WeatherServerReq.connection.getInputStream());
            if(bmp != null){
                result.setBmp(bmp);
            }
            success = true;
        } else {
            Log.e(TAG, String.format("Fehler: ", WeatherServerReq.connection.getResponseCode()));
            throw new IOException(String.format("Beim Laden der Bilddatei: %s ist gewaltig was schief gelaufen!", url$));
        }
        WeatherServerReq.connection.disconnect();
        return success;
    }
}
