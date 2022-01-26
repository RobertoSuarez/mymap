package com.example.mymap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    public RequestQueue cola;
    private GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        cola = Volley.newRequestQueue(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.map = googleMap;


        map.addMarker(new MarkerOptions()
                .position(new LatLng(-34, 151))
                .title("Marker in Sydney")).setTag(0);

        map.addMarker(new MarkerOptions()
                .position(new LatLng(-0.10820363732123867, -78.47378477657662))
                .title("Marker in Ecuader")).setTag(1);
        //map.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        this.AddMarker();


        map.setInfoWindowAdapter(new InfoWindowLugar(this, "personal"));


    }

    public void AddMarker() {
        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                "https://my-json-server.typicode.com/RobertoSuarez/mymap/sitios",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson = new Gson();

                        // Deserializamos
                        Sitio[] sitios = gson.fromJson(response.toString(), Sitio[].class);

                        // Mostramos
                        for (int i = 0; i < sitios.length; i++) {
                            System.out.println(sitios[i].titulo);

                            map.addMarker(new MarkerOptions()
                                    .position(sitios[i].posicion)
                                    .title(sitios[i].titulo)).setTag(sitios[i].id);

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        cola.add(arrayRequest);
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