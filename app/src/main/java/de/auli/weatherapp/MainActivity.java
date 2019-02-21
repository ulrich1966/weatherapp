package de.auli.weatherapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
                if(city == null || city.getText().toString().isEmpty()){
                    city.setText("Eine Stadt eingeben!!!");
                }
                final Result result = WeatherServerReq.getWeather(city.getText().toString().toString());

                runOnUiThread(() -> {
                    mapp(result);
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
        Double temp = new Double(result.getMain().getTemp()) - 273.15;
        temperatur.setText(getString(R.string.temp_template, temp.intValue()));
        beschreibung = findViewById(R.id.beschreibung);
        beschreibung.setText(result.getWeather().get(0).getDescription());
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
