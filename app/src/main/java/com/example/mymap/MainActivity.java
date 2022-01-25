package com.example.mymap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.map = googleMap;


        map.addMarker(new MarkerOptions()
                .position(new LatLng(-34, 151))
                .title("Marker in Sydney")).setTag("sydney");

        map.addMarker(new MarkerOptions()
                .position(new LatLng(-0.10820363732123867, -78.47378477657662))
                .title("Marker in Ecuader")).setTag("ecuadro");
        //map.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        map.setInfoWindowAdapter(new InfoWindowLugar(this, "personal"));


    }

    public void onChangeTypeMap(View v) {
        // Dependiendo del tipo de mapa se va a iterar.
        System.out.println(map.getMapType());
        switch (map.getMapType())
        {
            case GoogleMap.MAP_TYPE_NORMAL:
                map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case GoogleMap.MAP_TYPE_SATELLITE:
                map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            case GoogleMap.MAP_TYPE_TERRAIN:
                map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case GoogleMap.MAP_TYPE_HYBRID:
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
        }
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        this.map.clear();

        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position(latLng);

        this.map.animateCamera(CameraUpdateFactory.newLatLng(latLng));

        Marker marker = this.map.addMarker(markerOptions);

        marker.showInfoWindow();
    }
}