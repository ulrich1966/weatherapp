package de.auli.weatherapp;

import android.graphics.Bitmap;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;

public class WeatherUtils {
    private static final String TAG = WeatherUtils.class.getSimpleName();
    private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=%1$s&lang=de&appid=%2$s";
    //private static final String KEY = String.format("%s", R.string.weather_key); --> Results in different Number!!!
    private static final String KEY = "40c5de3e98b083a9edc44d6a653de166";
    private static final String NAME = "name";
    private static final String WEATHER = "weather";
    private static final String DESCRIPTION = "description";
    private static final String ICON = "icon";
    private static final String MAIN = "main";
    private static final String TEMP = "temp";

    public static WeatherData getWeather(String city) throws JSONException, IOException {
        String name = null;
        String description = null;
        String icon = null;
        Double temp = null;
        JSONObject jsonObject = new JSONObject(getFromServer(String.format(URL, city, KEY)));
        if (jsonObject.has(NAME)) {
            name = jsonObject.getString(NAME);
        }
        if (jsonObject.has(WEATHER)) {
            JSONArray jsonArrayWeather = jsonObject.getJSONArray(WEATHER);
            if (jsonArrayWeather.length() > 0) {
                JSONObject jsonWeather = jsonArrayWeather.getJSONObject(0);
                if (jsonWeather.has(DESCRIPTION)) {
                    description = jsonWeather.getString(DESCRIPTION);
                }
                if (jsonWeather.has(ICON)) {
                    icon = jsonWeather.getString(ICON);
                }
            }
        }
        if (jsonObject.has(MAIN)) {
            JSONObject main = jsonObject.getJSONObject(MAIN);
            temp = main.getDouble(TEMP);
        }
        return new WeatherData(name, description, icon, temp);
    }

    public static String getFromServer(String url) throws IOException {
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
        String result = sb.toString();
        Log.d(TAG, result);
        return result;
    }

    public static Bitmap getImage(WeatherData w) throws IOException {
        String url$ = String.format("http://openweathermap.org/img/w/%s.png", w.getIcon());
        Log.d(TAG, String.format("Request goes to: %s", url$));
        URL req = new URL(url$);
        HttpURLConnection c = (HttpURLConnection) req.openConnection();
        Bitmap bmp = BitmapFactory.decodeStream(c.getInputStream());
        c.disconnect();
        return bmp;
    }
}
