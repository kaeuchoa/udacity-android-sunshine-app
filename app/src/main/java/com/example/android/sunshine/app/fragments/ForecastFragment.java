package com.example.android.sunshine.app.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.sunshine.app.R;

import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastFragment extends Fragment {
    private ArrayAdapter<String> forecastAdapter;
    private ListView forecastListView;

    public ForecastFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);
        ArrayList<String> forecastSampleData = new ArrayList<>();
        forecastSampleData.add("Hoje - Ensolarado -  31/17");
        forecastSampleData.add("Amanhã - Nublado -  29/17");
        forecastSampleData.add("Quarta - Chuvoso -  28/16");
        forecastSampleData.add("Quinta - Ensolarado -  31/17");
        forecastSampleData.add("Sexta - Encoberto com Nuvens -  30/25");
        forecastSampleData.add("Sábado - Parcialmente Nublado -  28/18");
        forecastSampleData.add("Domingo - Chuvoso -  26/18");


        forecastAdapter = new ArrayAdapter<>(getActivity(),
                R.layout.list_item_forecast, R.id.list_item_forecast_textview, forecastSampleData);

        forecastListView = rootView.findViewById(R.id.listview_forecast);
        forecastListView.setAdapter(forecastAdapter);


        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.forecastfragment,menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_refresh:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}