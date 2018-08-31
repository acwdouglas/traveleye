package com.example.andrew.saultdesttourismapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//Main Activity
public class MainActivity extends AppCompatActivity {


    //Public views for the cardview recycler
    public static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;

    //Object for the raw data to empty through
    public DestRaw raw = new DestRaw();

    //Arraylist for all location
    ArrayList<DestData> data = new ArrayList<DestData>();

    //For finding GPS location
    LocationManager mLocationManager;

    public double lat = 0, lon = 0;


    //For passing contexts
    Context context = this;
    Activity activity = this;

    Button btnLocate;
    Button btnSortDistance;
    Button btnCuratedSort;
    Button btnSortRating;
    Button btnMap;


    String LATITUDE = "lat001", LONGITUDE = "lng001";

    //All curated data, as well as places already inspected
    ArrayList<CuratedData> curatedData = new ArrayList<CuratedData>();
    ArrayList<String> knownIds = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Generates all local attractions

        if (savedInstanceState != null)
        {
            // Restore value of members from saved state
            lat = savedInstanceState.getInt(LATITUDE);
            lon = savedInstanceState.getInt(LONGITUDE);
        }

        //Populates the curated data arraylist
        generateValues();

        //Inits the locations
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //Checks permissions for location and internet
        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED)) {
            if (lat == 0 || lon == 0)
                mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, mLocationListener);
        }

        //Inits the buttons and map
        btnLocate = findViewById(R.id.btnLocate);
        btnSortDistance = findViewById(R.id.btnSortDistance);
        btnCuratedSort = findViewById(R.id.btnCurateDsort);
        btnSortRating = findViewById(R.id.btnSortRating);
        btnMap = findViewById(R.id.btnMap);


        //Locates user
        btnLocate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Checks permissions
                if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    Log.d("c", "" + "Did not skip");
                    Permissions.check(context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.INTERNET},

                            "Need location permissions for map use.", new Permissions.Options()
                                    .setSettingsDialogTitle("Warning!").setRationaleDialogTitle("Info"),
                            new PermissionHandler() {
                                @Override
                                public void onGranted() {


                                }
                            });
                    Log.d("c", "" + "Checked");
                    return;
                    //If all permissions are valid
                } else if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {

                    if(lat == 0 && lon == 0){
                        Toast.makeText(context, "Searching for location...", Toast.LENGTH_SHORT).show();
                        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, mLocationListener);
                    }else{
                        Toast.makeText(context, "Finding nearby attractions!", Toast.LENGTH_SHORT).show();
                    }
                    //Create recycler
                    recyclerView = findViewById(R.id.recyclerView);
                    recyclerView.setHasFixedSize(false);

                    layoutManager = new LinearLayoutManager(v.getContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());

                    //Log lat and lon to me
                    Log.d("LATLON: ", lat +" "+ lon);

                    //Clears old data
                    data.clear();


                    //Outdated for loop that needs to be removed when refactoring: OUT OF TIME
                    //Calls to generate locations using an id for destination type
                    for (int i = 0; i < 6; i++) {
                        //Inits
                        LocalLocations locales = new LocalLocations((float) lat, (float) lon, raw, data, context, activity, i, curatedData, knownIds);
                        locales.GenerateLocations();

                    }
                    //Clear known ids in case they click again
                    knownIds.clear();

                    //Populate card view
                    adapter = new RecycleAdapter(data, context, lat, lon);
                    recyclerView.setAdapter(adapter);

                    Log.d("oh", lat +" "+ lon);

                }

            }
        });
        //Sort by rating
        //TODO: reverse sort
        btnSortRating.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Collections.sort(data, new Comparator<DestData>() {
                    @Override
                    public int compare(DestData destData, DestData t1) {

                        if (destData.getRating() == t1.getRating())
                            return destData.getName().compareToIgnoreCase(t1.getName());

                        if (destData.getRating() > t1.getRating())
                            return -1;
                        else
                            return 1;
                    }
                });

                adapter = new RecycleAdapter(data, context, lat, lon);
                recyclerView.setAdapter(adapter);
            }
        });
        //Sort by curated
        //TODO: reverse sort
        btnCuratedSort.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Collections.sort(data, new Comparator<DestData>() {
                    @Override
                    public int compare(DestData destData, DestData t1) {

                        if (destData.getCurated() == t1.getCurated())
                            return destData.getName().compareToIgnoreCase(t1.getName());

                        if (destData.getCurated() == true && t1.getCurated() == false)
                            return -1;
                        else
                            return 1;
                    }
                });

                adapter = new RecycleAdapter(data, context, lat, lon);
                recyclerView.setAdapter(adapter);
            }
        });
        //Sort by distance
        //TODO: reverse sort
        btnSortDistance.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Collections.sort(data, new Comparator<DestData>() {
                    @Override
                    public int compare(DestData destData, DestData t1) {

                        if (destData.getDistance() == t1.getDistance())
                            return destData.getName().compareToIgnoreCase(t1.getName());

                        if (destData.getDistance() > t1.getDistance())
                            return 1;
                        else
                            return -1;
                    }
                });

                adapter = new RecycleAdapter(data, context, lat, lon);
                recyclerView.setAdapter(adapter);
            }
        });

        //Opens up map overview
        btnMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    Log.d("c", "" + "Did not skip");
                    Permissions.check(context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.INTERNET},

                            "Location needed for map.", new Permissions.Options()
                                    .setSettingsDialogTitle("Warning!").setRationaleDialogTitle("Info"),
                            new PermissionHandler() {
                                @Override
                                public void onGranted() {


                                }
                            });
                    Log.d("c", "" + "Checked");
                    return;
                } else {

                    if (data.isEmpty()) {
                        Toast.makeText(context, "Must send Locations first!", Toast.LENGTH_SHORT).show();
                    } else {
                        //Call map overview activity
                        Log.d("oh", lat +" "+ lon);
                        Intent intent = new Intent(context, MainMapActivity.class);
                        intent.putExtra("DestData", data);
                        intent.putExtra("Lat", lat);
                        intent.putExtra("Lng", lon);
                        context.startActivity(intent);
                    }


                }

            }
        });

    }

    //TODO: implement data saving and caching
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        // Save the user's current game state
        savedInstanceState.putDouble(LATITUDE, lat);
        savedInstanceState.putDouble(LONGITUDE, lon);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    //Gets location
    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        //On location change, stop requesting
        public void onLocationChanged(android.location.Location location) {
            if ((ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(context, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED)) {

            }
            lat = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLatitude();
            lon = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLongitude();

            Log.d("LAT", "" + lat);
            Log.d("LON", "" + lon);

            Toast.makeText(context, "Location found!", Toast.LENGTH_SHORT).show();

            mLocationManager.removeUpdates(mLocationListener);

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }

    };

    private void generateValues(){

        String [] idArray = getResources().getStringArray(R.array.ids);
        int[] ratingArray = getResources().getIntArray(R.array.ratings);
        String [] shortArray = getResources().getStringArray(R.array.shortDescs);
        String [] longArray = getResources().getStringArray(R.array.fullDescs);


        for(int i = 0; i < idArray.length; i++){
            double d = ratingArray[i]/10;
            curatedData.add(new CuratedData(idArray[i], longArray[i], shortArray[i], d));
            Log.i("Array val", curatedData.get(i).getDescription());
        }
    }

}