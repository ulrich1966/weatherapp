package de.auli.weatherapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;

import de.auli.weatherapp.model.Data;
import de.auli.weatherapp.model.Result;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText city;
    private ImageView image;
    private TextView temperatur, beschreibung;

    /**
     * Startpunkt
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Verbinung pruefen / exit on disconnect
        if (!istNetzwerkVerfuegbar()) {
            finish();
        }

        //Layout bauen
        setContentView(R.layout.activity_main);
        city = findViewById(R.id.city);
//        image = findViewById(R.id.image);
//        temperatur = findViewById(R.id.temperatur);
//        beschreibung = findViewById(R.id.beschreibung);

        // Action fuer den Button / Thread erstellen / runOnUiThread() ueberschreiben und Thread und
        // starten
        final Button button = findViewById(R.id.button);
        button.setOnClickListener((v) -> new Thread(() -> {
            try {
                if (city == null || city.getText().toString().isEmpty()) {
                    city.setText("Eine Stadt eingeben!!!");
                }
                final Result result = WeatherServerReq.getWeather(city.getText().toString().toString());

                runOnUiThread(() -> {
                    mapp(result);
                    DataShare.getInstance().setResult(result);
//                    city.setText(weather.getName());
//                    image.setImageBitmap(bitmapWeather);
//                    beschreibung.setText(weather.getDescription());
//                    Double temp = weather.getTemp() - 273.15;
//                    temperatur.setText(getString(R.string.temp_template, temp.intValue()));
                });

            } catch (Exception e) {
                Log.e(TAG, "getWeather()", e);
            }
        }).start());

        city.setOnEditorActionListener(
                (textView, i, keyEvent) -> {
                    button.performClick();
                    return true;
                });
    }

    private void mapp(Result result) {
        image = findViewById(R.id.image);
        image.setImageBitmap(result.getBmp());
        temperatur = findViewById(R.id.temperatur);
        temperatur.setText(getString(R.string.temp_template, result.getMain().getTemp()));
        beschreibung = findViewById(R.id.beschreibung);
        beschreibung.setText(result.getWeather().get(0).getDescription());

        DataAdapter adapter = new DataAdapter(this, new ArrayList<Data>());
        //View fgmRoot = findViewById(R.id.list_data);
        ListView listView = (ListView) findViewById(R.id.list_data);
        listView.setAdapter(adapter);
        adapter.clear();
        adapter.addAll(addDatas(result));

        //return fgmRoot;
    }

    private ArrayList<Data> addDatas(Result result) {
        ArrayList<Data> datas = new ArrayList<Data>();
        datas.add(new Data("Längengrad:", result.getCoord().getLon()));
        datas.add(new Data("Breitengrad:", result.getCoord().getLat()));
        datas.add(new Data("Luftdruck:", String.format("%s hP", result.getMain().getPressure())));
        datas.add(new Data("Luftfeuchtigkeit:", String.format("%s %s", result.getMain().getHumidity(), "%")));
        datas.add(new Data("Minimaltemperatur:", getString(R.string.temp_template, result.getMain().getTemp_min())));
        datas.add(new Data("Maximaltemperatur:", getString(R.string.temp_template, result.getMain().getTemp_max())));
        datas.add(new Data("Sicht:", String.format("%s m", result.getMain().getHumidity())));
        datas.add(new Data("Windgeschwindigkeit:", String.format("%s m/s", result.getWind().getSpeed())));
        datas.add(new Data("Windrichtung:", getString(R.string.deg_template, result.getWind().getDeg())));
        datas.add(new Data("Sonnenaufgeng:", String.format("%s Uhr", result.getSys().getSunrise())));
        datas.add(new Data("Sonnenuntergang:", String.format("%s Uhr", result.getSys().getSunset())));

        return datas;

    }

    private boolean istNetzwerkVerfuegbar() {
        ConnectivityManager mgr = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mgr = getSystemService(ConnectivityManager.class);
        } else {
            mgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        NetworkInfo info = mgr == null ? null : mgr.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }
}
