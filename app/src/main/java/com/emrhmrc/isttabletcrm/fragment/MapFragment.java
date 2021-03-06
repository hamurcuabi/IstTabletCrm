package com.emrhmrc.isttabletcrm.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.CustomInfoWindowAdapter;
import com.emrhmrc.isttabletcrm.helper.CalendarEventsSingleton;
import com.emrhmrc.isttabletcrm.helper.MapGo;
import com.emrhmrc.isttabletcrm.helper.Methodes;
import com.emrhmrc.isttabletcrm.models.MapModel;
import com.emrhmrc.isttabletcrm.util.StringUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;

import java.util.ArrayList;
import java.util.Calendar;

public class MapFragment extends DialogFragment implements GoogleMap.OnMarkerClickListener {

    MapView mMapView;
    private GoogleMap googleMap;
    private ArrayList<MapModel> map;
    private int w, h;
    private ImageView img_close;
    private Button btn_before, btn_next;
    private MapGo mapGo;
    private MarkerOptions marker;

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
      /*  map = new ArrayList<>();
        map.add(new MapModel(37.1, 40.0, "title1", "descp1"));
        map.add(new MapModel(27.1, 29.0, "title2", "descp2"));
        map.add(new MapModel(33.1, 35.0, "title3", "descp3"));
        map.add(new MapModel(34.1, 42.0, "title4", "descp4"));*/
        w = getArguments().getInt("w");
        h = getArguments().getInt("h");
        mMapView = rootView.findViewById(R.id.mapview);
        btn_before = rootView.findViewById(R.id.btn_before);
        btn_next = rootView.findViewById(R.id.btn_next);
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

        mMapView.getMapAsync(mMap -> {
            googleMap = mMap;

            if (Methodes.checkAndRequestPermissions(getActivity())) {
                if (ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                googleMap.setMyLocationEnabled(true);
                googleMap.setOnMarkerClickListener(this::onMarkerClick);
            }

            // For dropping a marker at a point on the Map
            // create marker
            int i = 0;
            for (MapModel current : map
            ) {
                i++;
                LatLng gps = new LatLng(current.getLatitude(), current.getLongitude());
                marker = new MarkerOptions().position(gps).title(current.getTitle());

                IconGenerator iconGenerator = new IconGenerator(getActivity());
                //  iconGenerator.setBackground(getActivity().getDrawable(R.drawable.gps_marker));
                iconGenerator.setTextAppearance(getActivity(), R.style.iconGenText);
                Bitmap bitmap = iconGenerator.makeIcon(StringUtil.convertIntToString(i));
                marker.icon(BitmapDescriptorFactory.fromBitmap(bitmap));
                // marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.gps_marker));
                googleMap.addMarker(marker.position(gps)).setSnippet(current.getDescp());
                googleMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(getActivity()));


            }

            // For zooming automatically to the location of the marker
            /*CameraPosition cameraPosition = new CameraPosition.Builder().target(gps).zoom
                    (8).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));*/
        });
        if (CalendarEventsSingleton.getInstance().getSelected() != null) {
            Calendar calendar = CalendarEventsSingleton.getInstance().getSelected();
            Calendar before = (Calendar) calendar.clone();
            before.add(Calendar.DAY_OF_MONTH, -1);
            Calendar next = (Calendar) calendar.clone();
            next.add(Calendar.DAY_OF_MONTH, 1);

            btn_before.setOnClickListener(view -> {
                mapGo.goToDay(before);
                dismiss();
            });
            btn_next.setOnClickListener(view -> {
                dismiss();
                mapGo.goToDay(next);
            });
        }

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);
        mapGo = (MapGo) getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = w;
        params.height = h;
        getDialog().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
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

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (!marker.isInfoWindowShown()) marker.showInfoWindow();
        return false;
    }
}