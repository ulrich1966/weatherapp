package de.auli.weatherapp;

import org.junit.Test;

import java.text.MessageFormat;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    //private static final String URL ="http://api.openweathermap.org/data/2.5/weather?q=%s&lang=de&appid=%s";
    private static final String URL ="http://api.openweathermap.org/data/2.5/weather?q=%1$s&lang=de&appid=%2$s";
    private static final String KEY = "40c5de3e98b083a9edc44d6a653de166";
    private static final String KEY2 = ""+R.string.weather_key;
    @Test
    public void addition_isCorrect() {
        String req = String.format(URL, "Bremen", KEY);
        String req2 = String.format(URL, "Bremen", KEY2);
        System.out.println(req);
        System.out.println(req2);
    }
}