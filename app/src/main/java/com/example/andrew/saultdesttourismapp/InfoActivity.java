package com.example.andrew.saultdesttourismapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Andrew on 2018-04-02.
 */



    public class InfoActivity extends FragmentActivity implements OnMapReadyCallback{

    //All UI
    public TextView priceText;
    public TextView webText;
    public TextView phoneText;
    public TextView addressText;
    public TextView descText;
    public RatingBar ratingBar;
    public TextView titleText;
    public TextView openText;
    public TextView distText;
    public TextView openHourText;


    //Destination selected
    int position;

    MainActivity main = new MainActivity();
    DestData data;

    double userLat, userLng;


    GoogleMap map;

    Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_layout);

        //Unpack relevant data
        Bundle extras = getIntent().getExtras();
        position = extras.getInt("pos");
        data = (DestData) extras.getSerializable("DestData");
        userLat = extras.getDouble("lat");
        userLng = extras.getDouble("lon");

        //Build map frag
        Log.d("MAIN LAT", main.lat + "");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync( this);
        Log.d("MAP FRAG COMPLETE", "At least I don't suck this much!");


        //init
        this.titleText = findViewById(R.id.titleText2);
        this.descText = findViewById(R.id.descText2);
        this.ratingBar = findViewById(R.id.ratingBar2);
        this.webText =  findViewById(R.id.webText2);
        this.addressText = findViewById(R.id.addressText2);
        this.phoneText = findViewById(R.id.phoneText2);
        this.priceText = findViewById(R.id.priceText2);
        this.distText = findViewById(R.id.distText2);
        this.openText = findViewById(R.id.openText2);
        this.openHourText = findViewById(R.id.openHrsText);

        //Set all values
        titleText.setText(data.getName());
        descText.setText(data.getDesc());
        ratingBar.setRating((float) data.getRating());
        distText.setText("Distance: " + data.getDistance() + "km away");
        Drawable draw = ratingBar.getProgressDrawable();
        //Colour the curateds with blue
        if(data.getCurated() == true){
            draw.setColorFilter(Color.parseColor("#0064A8"), PorterDuff.Mode.SRC_ATOP);
        }else{
            draw.setColorFilter(Color.MAGENTA, PorterDuff.Mode.SRC_ATOP);
        }
        if(data.getWebsite() != null)
            webText.setText("Website: " + data.getWebsite());
        if(data.getPhone() != null)
            phoneText.setText("Phone number: " + data.getPhone());
        addressText.setText(data.getAddress());
        String s = "";
        if(data.openData == true){
            openText.setText("Currently ");
            if(data.getOpenNow() == true){
                s += "open.";
                openText.append(s);
                openText.setTextColor(Color.GREEN);
            }else{
                s += "closed.";
                openText.append(s);
                openText.setTextColor(Color.RED);
            }
            s="";
            for(int i = 0; i < data.times.length; i++){
                s+= data.times[i] + "\n";
            }
            openHourText.setText(s);
        }

    }

    @Override
    public void onMapReady(GoogleMap map) {
        Log.d("MAP READY", "Wow we made it!");


        double lat = data.getLat();
        double lng = data.getLng();
        LatLng latLng = new LatLng(lat, lng);
        LatLng local = new LatLng(userLat, userLng);

        //Add the markers for the user and the selected place
        map.addMarker(new MarkerOptions().position(latLng).title(data.getName()));
        BitmapDescriptor user = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);
        map.addMarker(new MarkerOptions().position(local).title("Your Locations").icon(user));


        //zoom in on place area
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f));
    }
}
