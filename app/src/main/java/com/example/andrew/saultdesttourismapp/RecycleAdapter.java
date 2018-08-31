package com.example.andrew.saultdesttourismapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Andrew on 2018-03-12.
 */

//PARTIALLY AUTO MADE/SET IN STONE

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder>  {

    private ArrayList<DestData> mDataset;
    Context context;
    double lati, longi;

    int caller;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView descTextView;
        public TextView nameTextView;
        public RatingBar ratedRatingBar;
        public ImageView peekImageView;
        public TextView addressText;
        public TextView distText;





        public ViewHolder(View v) {
            super(v);
            //init
            this.descTextView = (TextView) v.findViewById(R.id.descText);
            this.nameTextView = (TextView) v.findViewById(R.id.titleText);
            this.ratedRatingBar = (RatingBar) v.findViewById(R.id.ratingBar);
            this.peekImageView = (ImageView) v.findViewById(R.id.imageView);
            this.addressText = v.findViewById(R.id.addressText);
            this.distText = v.findViewById(R.id.disText);
        }


    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecycleAdapter(ArrayList<DestData> myDataset, Context context, double lati, double longi) {
        mDataset = myDataset;
        this.context = context;
        this.lati = lati;
        this.longi = longi;
        this.caller = caller;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view

            View v = (View) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cardview_layout, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        TextView descTextView = holder.descTextView;
        TextView nameTextView = holder.nameTextView;
        RatingBar ratingBarView = holder.ratedRatingBar;
        ImageView peekImageView = holder.peekImageView;
        TextView distText = holder.distText;
        TextView addressText = holder.addressText;




            //Fill out card info
            addressText.setText(mDataset.get(position).getAddress());
            distText.setText(mDataset.get(position).getDistance() + "km away");
            descTextView.setText("" + mDataset.get(position).getShortDesc());
            nameTextView.setText(mDataset.get(position).getName());
            ratingBarView.setRating((float) mDataset.get(position).getRating());
            Drawable draw = ratingBarView.getProgressDrawable();
            //If curated, blue!
            if(mDataset.get(position).getCurated()){
                draw.setColorFilter(Color.parseColor("#0064A8"), PorterDuff.Mode.SRC_ATOP);
            }else{
                draw.setColorFilter(Color.MAGENTA, PorterDuff.Mode.SRC_ATOP);
            }
            //Image loader, using Picasoo library. Try/catch necessary
            try {
                String photoURL = "https://maps.googleapis.com/maps/api/place/photo?";

                photoURL += "maxheight=" + 550 + "&";

                photoURL += "maxwidth=" + mDataset.get(position).pWidth + "&";

                photoURL += "photoreference=" + mDataset.get(position).photoRef + "&";

                photoURL += "key=" + "AIzaSyDHRRpnbaWDPQAubr8LFldLX_dEth_HykU";

                Picasso.get().load(photoURL).into(peekImageView);
            } catch (Exception e) {
                Log.d("No Photos", "onBindViewHolder: ");
            }


        //On title click, move to info activity
        holder.nameTextView.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick (View view){
            Intent intent = new Intent(context, InfoActivity.class);
            intent.putExtra("pos", position);
            intent.putExtra("DestData" , mDataset.get(position));
            intent.putExtra("lat", lati);
            intent.putExtra("lon", longi);
            context.startActivity(intent);

        }
    });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
