package com.example.dmitry.quickbike.architecture.component;

import java.util.ArrayList;
import java.util.List;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapViewModel extends ViewModel {

    private MutableLiveData<List<MarkerOptions>> mMarkersOptions;

    public LiveData<List<MarkerOptions>> getMarkersOptions() {
        if (mMarkersOptions == null) {
            mMarkersOptions = new MutableLiveData<List<MarkerOptions>>();
            loadMarkers();
        }
        return mMarkersOptions;
    }

    private void loadMarkers() {
        // do async operation to fetch users
        List<MarkerOptions> markerOptions = new ArrayList<>();

        LatLng brest = new LatLng(52.09755, 23.68775);
        LatLng gomel = new LatLng(52.4345, 30.9754);
        LatLng mogilev = new LatLng(53.900716, 30.33136);

        markerOptions.add(new MarkerOptions().position(brest).title("Brest"));
        markerOptions.add(new MarkerOptions().position(gomel).title("Gomel"));
        markerOptions.add(new MarkerOptions().position(mogilev).title("Mogilev"));
        mMarkersOptions.setValue(markerOptions);
    }
}
