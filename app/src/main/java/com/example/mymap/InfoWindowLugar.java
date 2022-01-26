package com.example.mymap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class InfoWindowLugar implements GoogleMap.InfoWindowAdapter {

    Context context;
    LayoutInflater inflater;
    String titulo;
    View mWindow;

    public InfoWindowLugar(Context context, String titulo) {
        this.context = context;
        this.titulo = titulo;

        mWindow = LayoutInflater.from(context).inflate(R.layout.windowinfo, null);
    }

    @Nullable
    @Override
    public View getInfoContents(@NonNull Marker marker) {
        return null;
    }

    @Nullable
    @Override
    public View getInfoWindow(@NonNull Marker marker) {
        int ID = 0;
        try {
            ID = (int)marker.getTag();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return this.mWindow;
        }

        Sitio sitio = this.GetSitionAPI(ID);
        if (ID == 0) {
            sitio.Titulo = "Noruega";
        }

        ((TextView) mWindow.findViewById(R.id.tvTitle)).setText(sitio.Titulo);





        return mWindow;
    }

    // Trae los datos desde la api
    public Sitio GetSitionAPI(int ID) {
        return new Sitio(){{
                ID = 1;
                Titulo = "Ecuador";
                Descripcion = "Ecuador es un Pais";
                UrlImage = "";
                Faculta = "Ciencia de la ingenieria";
                Decano = "Roberto El doc";
                Ubicacion = "Quevedo ciudad de la furia";
                Posicion = new LatLng(-0.10820363732123867, -78.47378477657662);
        }};
    }
    

}
