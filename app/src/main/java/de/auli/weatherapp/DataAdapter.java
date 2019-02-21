package de.auli.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import de.auli.weatherapp.model.Data;

public class DataAdapter extends ArrayAdapter<Data> {

    public DataAdapter(Context context, ArrayList<Data> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Data data = getItem(position);
        if (convertView == null) {
            // R.layout.item_data ist die xml Datei in der zwei TextVies definiert sind.
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_data, parent, false);
        }
        TextView key = (TextView) convertView.findViewById(R.id.txt_lable);
        TextView  value = (TextView) convertView.findViewById(R.id.txt_data);
        key.setText(data.key);
        value.setText(data.value);
        return convertView;
    }
}
