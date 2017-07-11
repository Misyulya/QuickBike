package com.example.dmitry.quickbike.fragment;

import java.util.List;

import com.google.android.gms.maps.model.Marker;

public interface IMapView {
    void addMarkersToMap(List<Marker> markers);
}
