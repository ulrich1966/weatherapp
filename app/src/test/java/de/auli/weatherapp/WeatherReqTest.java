package de.auli.weatherapp;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import de.auli.weatherapp.model.Result;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class WeatherReqTest {
    //private static final String URL ="http://api.openweathermap.org/data/2.5/weather?q=%s&lang=de&appid=%s";
    private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=%1$s&lang=de&appid=%2$s";
    private static final String KEY = "40c5de3e98b083a9edc44d6a653de166";
    private static final String KEY2 = "" + R.string.weather_key;

    @Test
    public void addition_isCorrect() throws Exception {
        final Result result = WeatherServerReq.getWeather("Bremen", false);
        if(result != null)   {
            System.out.println(result.toString());
        }


    }
}