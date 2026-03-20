package com.example.gogo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gogo.model.Place;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {

    private final List<Place> places;
    private final Context context;

    public PlaceAdapter(Context context, List<Place> places) {
        this.context = context;
        this.places = places;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_place, parent, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        Place place = places.get(position);
        holder.tvName.setText(place.getName());
        holder.tvAddress.setText(place.getAddress());
        holder.ratingBar.setRating(place.getRating());
        Glide.with(context)
                .load(place.getImageUrl())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.ivImage);
        holder.itemView.setOnClickListener(v -> openDetail(place));
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    private void openDetail(Place place) {
        Intent intent = new Intent(context, PlaceDetailActivity.class);
        intent.putExtra(PlaceDetailActivity.EXTRA_PLACE_ID, place.getId());
        intent.putExtra(PlaceDetailActivity.EXTRA_PLACE_NAME, place.getName());
        intent.putExtra(PlaceDetailActivity.EXTRA_PLACE_DESCRIPTION, place.getDescription());
        intent.putExtra(PlaceDetailActivity.EXTRA_PLACE_RATING, place.getRating());
        intent.putExtra(PlaceDetailActivity.EXTRA_PLACE_IMAGE_URL, place.getImageUrl());
        intent.putExtra(PlaceDetailActivity.EXTRA_PLACE_LATITUDE, place.getLatitude());
        intent.putExtra(PlaceDetailActivity.EXTRA_PLACE_LONGITUDE, place.getLongitude());
        intent.putExtra(PlaceDetailActivity.EXTRA_PLACE_ADDRESS, place.getAddress());
        context.startActivity(intent);
    }

    static class PlaceViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvName;
        TextView tvAddress;
        RatingBar ratingBar;

        PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.iv_place_image);
            tvName = itemView.findViewById(R.id.tv_place_name);
            tvAddress = itemView.findViewById(R.id.tv_place_address);
            ratingBar = itemView.findViewById(R.id.rating_bar);
        }
    }
}
