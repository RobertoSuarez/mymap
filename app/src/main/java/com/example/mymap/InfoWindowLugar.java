package com.example.mymap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
            sitio.titulo = "Noruega";
        }

        ((TextView) mWindow.findViewById(R.id.tvTitle)).setText(sitio.titulo);





        return mWindow;
    }

    // Trae los datos desde la api
    public Sitio GetSitionAPI(int ID) {
        return new Sitio(){{
                id = 1;
                titulo = "Ecuador";
                descripcion = "Ecuador es un Pais";
                urlImage = "";
                faculta = "Ciencia de la ingenieria";
                decano = "Roberto El doc";
                ubicacion = "Quevedo ciudad de la furia";
                posicion = new LatLng(-0.10820363732123867, -78.47378477657662);
        }};
    }
    

}
