package de.auli.weatherapp;

import org.junit.Test;

import java.io.IOException;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class WeatherReqTest {
    //private static final String URL ="http://api.openweathermap.org/data/2.5/weather?q=%s&lang=de&appid=%s";
    private static final String URL ="http://api.openweathermap.org/data/2.5/weather?q=%1$s&lang=de&appid=%2$s";
    private static final String KEY = "40c5de3e98b083a9edc44d6a653de166";
    private static final String KEY2 = ""+R.string.weather_key;
    @Test
    public void addition_isCorrect() throws IOException {
        String req = String.format(URL, "Bremen", KEY);
        System.out.println(req);
        WeatherUtils wu = new WeatherUtils();
        String result = wu.getFromServer(req);
        System.out.println(result);

    }
}