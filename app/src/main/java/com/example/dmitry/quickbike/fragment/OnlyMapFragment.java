package com.example.dmitry.quickbike.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dmitry.quickbike.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class OnlyMapFragment extends BaseFragment implements OnMapReadyCallback {

    private Unbinder mUnbinder;
    private GoogleMap mMap;
    private String title;
    private int page;
    public static final String TITLE = "title";
    public static final String PAGE_NUMBER = "page number";

    @BindView(R.id.mapView) MapView mMapView;
    @BindView(R.id.tv_label) TextView tvLabel;

    // newInstance constructor for creating fragment with arguments
    public static OnlyMapFragment newInstance(int page, String title) {
        OnlyMapFragment onlyMapFragment = new OnlyMapFragment();
        Bundle args = new Bundle();
        args.putInt(PAGE_NUMBER, page);
        args.putString(TITLE, title);
        onlyMapFragment.setArguments(args);
        return onlyMapFragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt(PAGE_NUMBER, 0);
        title = getArguments().getString(TITLE);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        tvLabel.setText(page + " -- " + title);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapView.getMapAsync(this);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng minsk = new LatLng(53.900, 27.567);

        mMap.addMarker(new MarkerOptions().position(minsk).title("The best place to live"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(minsk));
    }
}
