package com.emrhmrc.isttabletcrm.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.helper.Methodes;
import com.emrhmrc.isttabletcrm.models.MapModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends DialogFragment {

    MapView mMapView;
    private GoogleMap googleMap;
    private MapModel map;
    private int w, h;

    public static MapFragment newInstance(MapModel map, int w, int h) {

        Bundle args = new Bundle();
        args.putSerializable("map", map);
        args.putInt("w", w);
        args.putInt("h", h);
        MapFragment fragment = new MapFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.map_fragment, container, false);
        map = (MapModel) getArguments().getSerializable("map");
        w = getArguments().getInt("w");
        h = getArguments().getInt("h");
        mMapView = rootView.findViewById(R.id.mapview);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                if (Methodes.checkAndRequestPermissions(getActivity())) {
                    googleMap.setMyLocationEnabled(true);
                }

                // For dropping a marker at a point on the Map
                LatLng sydney = new LatLng(map.getLatitude(), map.getLongitude());
                googleMap.addMarker(new MarkerOptions().position(sydney).title(map.getTitle()).snippet
                        (map.getDescp()));

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom
                        (6).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = w;
        params.height = h;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
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
}