package com.example.andrew.saultdesttourismapp;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.StrictMode;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * Created by Andrew on 2018-04-01.
 */

//This class is for retrieving the local information
public class LocalLocations {

    float lat, lon;
    DestRaw raw;
    ArrayList<DestData> data = new ArrayList<DestData>();
    Context context;
    Activity activity;

    public static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    int n;
    PlaceData pData;
    ArrayList<CuratedData> curr = new ArrayList<CuratedData>();


    Gson gson;

    ArrayList<String> knownIds = new ArrayList<String>();

    public LocalLocations(float lat, float lon, DestRaw raw, ArrayList<DestData> data, Context context, Activity activity, int n, ArrayList<CuratedData> curr, ArrayList<String> knownIds) {

        this.raw = raw;
        this.lat = lat;
        this.lon = lon;
        this.data = data;
        this.context = context;
        this.activity = activity;
        this.n = n;
        this.curr = curr;
        this.knownIds = knownIds;
    }

    //Generates local locations
    public void GenerateLocations() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Log.d("Locales Called", "GenerateLocations: ");
        HttpTransport httpTransport = new NetHttpTransport();
        HttpRequestFactory requestFactory = httpTransport.createRequestFactory();

        //Deprecated... works for now
        JSONParser parser = new JSONParser();

        //Creates the URL for the info request
        GenericUrl url = new GenericUrl("https://maps.googleapis.com/maps/api/place");
        url.appendRawPath("/nearbysearch/json");
        url.put("location", (lat + "," + lon));
        //25km
        url.put("radius", "25000");

        //Look for a different tag based on passed value
        switch (n) {
            case 0:
                url.put("type", "museum");
                break;
            case 1:
                url.put("type", "art_gallery");
                break;
            case 2:
                url.put("type", "amusement_park");
                break;
            case 3:
                url.put("type", "casino");
                break;
            case 4:
                url.put("type", "bowling_alley");
                break;
            case 5:
                url.put("type", "zoo");
                break;
            default:
                break;
        }


        Log.d("Locales Called 2", "GenerateLocations: ");

        //My access key for the google API
        url.put("key", ("AIzaSyDHRRpnbaWDPQAubr8LFldLX_dEth_HykU"));
        String str = url.toString();
        try {
            HttpRequest request = requestFactory.buildGetRequest(url);
            HttpResponse httpResponse = request.execute();
            JSONObject response = (JSONObject) parser.parse(httpResponse.parseAsString());
            gson = new Gson();

            //Parse to raw class
            raw = gson.fromJson(response.toString(), DestRaw.class);
            Log.d("Locales Called 3", ""+raw.getResults().size());
            Log.d("Locales Called 3", ""+url.toString());
        } catch (Exception e) {
            Log.e("First URL Exception", "GenerateLocations: ", e);
        }

        Log.i("Hmm", "" + raw.getResults().size());

        //Individually parse every object inside raw
        for (int i = 0; i < raw.getResults().size(); i++) {

            //skip if already seen
            if (knownIds.contains(raw.getResults().get(i).getPlaceId())){
                Log.i("Continuing", "GenerateLocations: ");
                continue;
            }
            //Add to known ids to look for duplicates
            knownIds.add(raw.getResults().get(i).getPlaceId());

            //Populate
            DestData tempData = new DestData((raw.getResults().get(i).getPlaceId()));
            tempData.setLat(raw.getResults().get(i).getGeometry().getLocation().getLat());
            tempData.setLng(raw.getResults().get(i).getGeometry().getLocation().getLng());
            tempData.setName(raw.getResults().get(i).getName());
            tempData.setVicinity(raw.getResults().get(i).getVicinity());

            //Photos may not exist, catch for ease
            try {
                tempData.setpHeight(raw.getResults().get(i).getPhotos().get(0).getHeight());
                tempData.setpWidth(raw.getResults().get(i).getPhotos().get(0).getWidth());
                tempData.setPhotoRef(raw.getResults().get(i).getPhotos().get(0).getPhotoReference());
            } catch (Exception ex) {
                tempData.photoData = false;
            }

            //Create generic url for JSON Parsing
            url = new GenericUrl("https://maps.googleapis.com/maps/api/place/details/json?");
            url.put("placeid", (raw.getResults().get(i).getPlaceId()));
            url.put("key", ("AIzaSyDHRRpnbaWDPQAubr8LFldLX_dEth_HykU"));

            //Parse Google Places API
            try {
                HttpRequest request = requestFactory.buildGetRequest(url);
                HttpResponse httpResponse = request.execute();
                JSONObject response = (JSONObject) parser.parse(httpResponse.parseAsString());
                gson = new Gson();
                pData = new PlaceData();
                pData = gson.fromJson(response.toString(), PlaceData.class);
            } catch (Exception e) {
                Log.e("First URL Exception", "GenerateLocations: ", e);
            }

            //Get info
            tempData.setAddress(pData.getResults().getFormattedAddress());
            tempData.setPhone(pData.getResults().getFormattedPhoneNumber());
            tempData.setIntlPhone(pData.getResults().getInternationalPhoneNumber());


            tempData.setWebsite(pData.getResults().getWebsite());
            if(tempData.getWebsite() != null){

            }else
                tempData.setWebsite("");
            if(tempData.getWebsite().length() > 60)
                tempData.setWebsite("");

            tempData.setRating(raw.getResults().get(i).getRating());

            try {
                tempData.setOpenNow(pData.getResults().getmOpeningHours().getOpenNow());
            } catch (NullPointerException ex) {
                Log.d("", "Null Pointer");

            }

            try {
                tempData.times = pData.getResults().getmOpeningHours().getWeekdayText().toArray(new String[7]);
            } catch (NullPointerException ex) {
                Log.d("", "Null Pointer");
                tempData.openData = false;
            }


            //Check if any ids match curated ids
            for(int j = 0; j < 2; j++){
                Log.d(curr.get(j).getId(), "GenerateLocations: ");
                Log.d(raw.getResults().get(i).getPlaceId(), tempData.shortDesc);
                if(curr.get(j).getId().compareTo(raw.getResults().get(i).getPlaceId()) == 0){

                    tempData.setRating(tempData.getRating() * 0.3 + curr.get(j).getRating() * 0.7);
                    tempData.setShortDesc(curr.get(j).getShortDesc());
                    tempData.setDesc(curr.get(j).getDescription());
                    tempData.setCurated(true);

                    Log.d(curr.get(j).getId(), "GenerateLocations: ");
                    Log.d(raw.getResults().get(i).getPlaceId(), tempData.shortDesc);
                }
            }


            android.location.Location loc = new Location("locA");
            loc.setLatitude(lat);
            loc.setLongitude(lon);
            android.location.Location loc2 = new Location("locB");
            loc2.setLatitude(tempData.getLat());
            loc2.setLongitude(tempData.getLng());

            tempData.setDistance(loc.distanceTo(loc2)/1000);
            tempData.setDistance(Double.parseDouble(new DecimalFormat("##.##").format(tempData.getDistance())));


            //Adds to arraylist
            data.add(tempData);

        }


    }

}
