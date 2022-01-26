package com.example.mymap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
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
        Sitio sitio;
        try {
            sitio = (Sitio)marker.getTag();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return this.mWindow;
        }

        ImageView image = (ImageView)mWindow.findViewById(R.id.image);

        ((TextView) mWindow.findViewById(R.id.tvTitle)).setText(sitio.titulo);
        ((TextView) mWindow.findViewById(R.id.tvDescription)).setText(sitio.descripcion);
        ((TextView) mWindow.findViewById(R.id.tvfaculta)).setText(sitio.faculta);
        ((TextView) mWindow.findViewById(R.id.tvdecano)).setText(sitio.decano);
        ((TextView) mWindow.findViewById(R.id.tvubicacion)).setText(sitio.ubicacion);

        Glide.with(mWindow).load(sitio.urlImage).into(image);



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
