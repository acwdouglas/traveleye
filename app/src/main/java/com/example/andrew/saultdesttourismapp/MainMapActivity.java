package com.example.andrew.saultdesttourismapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by Andrew on 2018-04-05.
 */

public class MainMapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    ArrayList<DestData> data;
    Context context = this;

    double userLat, userLng;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_map_frag);

        //Unpack variables
        Bundle extras = getIntent().getExtras();
        data = (ArrayList<DestData>) extras.getSerializable("DestData");
        userLat = extras.getDouble("Lat");
        userLng = extras.getDouble("Lng");


        //Build map frag
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        mapFragment.getMapAsync(this);
        Log.d("MAP FRAG COMPLETE", "At least I don't suck this much!");
    }

    public void onMapReady(GoogleMap map) {

        //Build all markers, SNIPPET IS FOR SELECTION INFO
        for(int i = 0; i < data.size(); i++){
            LatLng latLng = new LatLng(data.get(i).getLat(), data.get(i).getLng());
            map.addMarker(new MarkerOptions().position(latLng).title(data.get(i).getName()).snippet(String.valueOf(i)));
        }


        //Build user marker
        LatLng local = new LatLng(userLat, userLng);
        BitmapDescriptor user = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);
        map.addMarker(new MarkerOptions().position(local).title("Your Locations").icon(user).snippet("-1"));

        //Sets the info box adapter
        map.setInfoWindowAdapter(new myInfoWindowAdapter());
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(local, 12.0f));
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(context, "Hi!", Toast.LENGTH_SHORT);
    }

    class myInfoWindowAdapter implements GoogleMap.InfoWindowAdapter{

        private final View view;

        myInfoWindowAdapter(){
            view = getLayoutInflater().inflate(R.layout.infowindow_view, null);
        }

        @Override
        public View getInfoContents(Marker marker) {
            //Find which marker was clicked
            int i = Integer.parseInt(marker.getSnippet());

            //Inits
            TextView infoTitle = view.findViewById(R.id.infoTitle);
            RatingBar infoRating = view.findViewById(R.id.infoRating);
            TextView infoAddress = view.findViewById(R.id.infoAddress);
            TextView infoPhone = view.findViewById(R.id.infoPhone);
            TextView infoWebsite = view.findViewById(R.id.infoWebsite);
            TextView infoOpen = view.findViewById(R.id.infoOpen);
            TextView infoDistance = view.findViewById(R.id.infoDistance);

            //Easter egg!
            if(i == -1){

                infoTitle.setText("You are here~!");
                infoRating.setRating(5.0f);
                infoAddress.setText("You are a");
                infoPhone.setText("5/5 to me");
                infoWebsite.setText("any day!");

                return view;
            }


            //Populate infobox
            infoTitle.setText(marker.getTitle());
            infoRating.setRating((float) data.get(i).getRating());
            Drawable draw = infoRating.getProgressDrawable();
            if(data.get(i).getCurated() == true){
                draw.setColorFilter(Color.parseColor("#0064A8"), PorterDuff.Mode.SRC_ATOP);
            }else{
                draw.setColorFilter(Color.MAGENTA, PorterDuff.Mode.SRC_ATOP);
            }
            infoAddress.setText(data.get(i).getAddress());
            if(data.get(i).openData == true){
                infoOpen.setText("Currently ");
                if(data.get(i).openNow){
                    infoOpen.append("open.");
                    infoOpen.setTextColor(Color.GREEN); }
                else {
                    infoOpen.append("closed.");
                    infoOpen.setTextColor(Color.RED);
                }
            }
            infoPhone.setText(data.get(i).getPhone());
            infoWebsite.setText(data.get(i).getWebsite());
            infoDistance.setText(data.get(i).getDistance() + "km away");

            return view;

        }


        @Override
        public View getInfoWindow(Marker marker) {
            //AUTO GENERATED ZZZZ
            return null;
        }
    }
}
