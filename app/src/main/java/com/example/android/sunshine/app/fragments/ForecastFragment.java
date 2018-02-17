package com.example.android.sunshine.app.fragments;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.sunshine.app.R;
import com.example.android.sunshine.app.tasks.FetchWeatherTask;

import java.util.ArrayList;


/**
 * Fragmento para apresentar os dados da previsão do tempo em forma de lista
 */
public class ForecastFragment extends Fragment {
    private ArrayAdapter<String> forecastAdapter;
    private ListView forecastListView;

    // Id para identificar a permissão de internet
    private static final int PERMISSION_INTERNET_CODE = 0;


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
        final int ID = item.getItemId();
        switch (ID){
            case R.id.action_refresh:
                if (isInternetPermissionGranted())
                    new FetchWeatherTask().execute();
                else{
                    ActivityCompat.requestPermissions(
                            getActivity(),new String[]{Manifest.permission.INTERNET},PERMISSION_INTERNET_CODE);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_INTERNET_CODE:
                if(grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getContext(), "Permissão Concedida", Toast.LENGTH_SHORT).show();
                    new FetchWeatherTask().execute();
                }else{
                    Toast.makeText(getContext(), "Permissão Negada", Toast.LENGTH_SHORT).show();
                }
        }
    }

    private boolean isInternetPermissionGranted(){
        final int permission = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.INTERNET);
        return permission == PackageManager.PERMISSION_GRANTED;
    }
}