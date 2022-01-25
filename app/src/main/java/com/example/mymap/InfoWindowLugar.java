package com.example.mymap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.GoogleMap;
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

        System.out.println(marker.getTag());
        TextView title = (TextView) mWindow.findViewById(R.id.tvTitle);
        TextView description = (TextView) mWindow.findViewById(R.id.tvDescription);

        title.setText(marker.getTitle());
        description.setText(marker.getSnippet());


        return mWindow;
    }
}
