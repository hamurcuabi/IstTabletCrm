package com.emrhmrc.isttabletcrm.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.helper.Methodes;
import com.emrhmrc.isttabletcrm.models.MapModel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapFragment extends DialogFragment {

    MapView mMapView;
    private GoogleMap googleMap;
    private ArrayList<MapModel> map;
    private int w, h;
    private ImageView img_close;

    public static MapFragment newInstance(ArrayList<MapModel> map, int w, int h) {

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
        map = (ArrayList<MapModel>) getArguments().getSerializable("map");
        w = getArguments().getInt("w");
        h = getArguments().getInt("h");
        mMapView = rootView.findViewById(R.id.mapview);
        img_close = rootView.findViewById(R.id.img_close);
        img_close.setOnClickListener(view -> {
            dismiss();
        });
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
                // create marker
                for (MapModel current : map
                ) {
                    LatLng gps = new LatLng(current.getLatitude(), current.getLongitude());
                    MarkerOptions marker = new MarkerOptions().position(gps).title(current.getTitle());
                    marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.gps_marker));
                    googleMap.addMarker(marker.position(gps)).setSnippet(current.getDescp());
                }

                // For zooming automatically to the location of the marker
                /*CameraPosition cameraPosition = new CameraPosition.Builder().target(gps).zoom
                        (8).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));*/
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);
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