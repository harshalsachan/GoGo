package com.example.gogo;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;

import java.util.Locale;

public class PlaceDetailActivity extends AppCompatActivity {

    public static final String EXTRA_PLACE_ID = "extra_place_id";
    public static final String EXTRA_PLACE_NAME = "extra_place_name";
    public static final String EXTRA_PLACE_DESCRIPTION = "extra_place_description";
    public static final String EXTRA_PLACE_RATING = "extra_place_rating";
    public static final String EXTRA_PLACE_IMAGE_URL = "extra_place_image_url";
    public static final String EXTRA_PLACE_LATITUDE = "extra_place_latitude";
    public static final String EXTRA_PLACE_LONGITUDE = "extra_place_longitude";
    public static final String EXTRA_PLACE_ADDRESS = "extra_place_address";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);

        String name = getIntent().getStringExtra(EXTRA_PLACE_NAME);
        String description = getIntent().getStringExtra(EXTRA_PLACE_DESCRIPTION);
        float rating = getIntent().getFloatExtra(EXTRA_PLACE_RATING, 0f);
        String imageUrl = getIntent().getStringExtra(EXTRA_PLACE_IMAGE_URL);
        double latitude = getIntent().getDoubleExtra(EXTRA_PLACE_LATITUDE, 0);
        double longitude = getIntent().getDoubleExtra(EXTRA_PLACE_LONGITUDE, 0);
        String address = getIntent().getStringExtra(EXTRA_PLACE_ADDRESS);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(name);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ImageView ivImage = findViewById(R.id.iv_place_image);
        TextView tvName = findViewById(R.id.tv_place_name);
        RatingBar ratingBar = findViewById(R.id.rating_bar);
        TextView tvRatingValue = findViewById(R.id.tv_rating_value);
        TextView tvAddress = findViewById(R.id.tv_address);
        TextView tvDescription = findViewById(R.id.tv_description);
        Button btnOpenMaps = findViewById(R.id.btn_open_maps);

        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(ivImage);

        tvName.setText(name);
        ratingBar.setRating(rating);
        tvRatingValue.setText(String.format(Locale.getDefault(), "%.1f / 5.0", rating));
        tvAddress.setText(address);
        tvDescription.setText(description);

        btnOpenMaps.setOnClickListener(v -> openInMaps(name, latitude, longitude));
    }

    private void openInMaps(String name, double latitude, double longitude) {
        Uri geoUri = Uri.parse(String.format(Locale.US,
                "geo:%f,%f?q=%f,%f(%s)",
                latitude, longitude, latitude, longitude, Uri.encode(name)));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, geoUri);
        try {
            startActivity(mapIntent);
        } catch (ActivityNotFoundException e) {
            Uri browserUri = Uri.parse(String.format(Locale.US,
                    "https://www.google.com/maps/search/?api=1&query=%f,%f",
                    latitude, longitude));
            startActivity(new Intent(Intent.ACTION_VIEW, browserUri));
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
