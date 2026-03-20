package com.example.gogo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.gogo.model.Place;
import com.example.gogo.model.SampleData;

import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_map);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        List<Place> places = SampleData.getSamplePlaces();
        for (Place place : places) {
            LatLng latLng = new LatLng(place.getLatitude(), place.getLongitude());
            Marker marker = googleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(place.getName())
                    .snippet(place.getAddress()));
            if (marker != null) {
                marker.setTag(place);
            }
        }

        if (!places.isEmpty()) {
            Place first = places.get(0);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(first.getLatitude(), first.getLongitude()), 3f));
        }

        googleMap.setOnInfoWindowClickListener(marker -> {
            Object tag = marker.getTag();
            if (tag instanceof Place) {
                openDetail((Place) tag);
            }
        });
    }

    private void openDetail(Place place) {
        Intent intent = new Intent(this, PlaceDetailActivity.class);
        intent.putExtra(PlaceDetailActivity.EXTRA_PLACE_ID, place.getId());
        intent.putExtra(PlaceDetailActivity.EXTRA_PLACE_NAME, place.getName());
        intent.putExtra(PlaceDetailActivity.EXTRA_PLACE_DESCRIPTION, place.getDescription());
        intent.putExtra(PlaceDetailActivity.EXTRA_PLACE_RATING, place.getRating());
        intent.putExtra(PlaceDetailActivity.EXTRA_PLACE_IMAGE_URL, place.getImageUrl());
        intent.putExtra(PlaceDetailActivity.EXTRA_PLACE_LATITUDE, place.getLatitude());
        intent.putExtra(PlaceDetailActivity.EXTRA_PLACE_LONGITUDE, place.getLongitude());
        intent.putExtra(PlaceDetailActivity.EXTRA_PLACE_ADDRESS, place.getAddress());
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
