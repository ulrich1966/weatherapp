package de.auli.weatherapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import de.auli.weatherapp.model.Data;
import de.auli.weatherapp.model.Result;

public class FgmData extends Fragment {
    private static final String TAG = FgmData.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DataAdapter adapter = new DataAdapter(getContext(), new ArrayList<Data>());
        View fgmRoot = inflater.inflate(R.layout.fgm_datas, container, false);
        ListView listView = (ListView) fgmRoot.findViewById(R.id.list_data);
        listView.setAdapter(adapter);
        adapter.clear();
        adapter.addAll(addDatas());
        return fgmRoot;
    }

    private ArrayList<Data> addDatas() {
        ArrayList<Data> datas = new ArrayList<Data>();
        datas.add(new Data("Key Bla", "Value"));
        datas.add(new Data("Key Bla", "Value"));
        return datas;

    }
}
