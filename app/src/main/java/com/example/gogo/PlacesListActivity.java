package com.example.gogo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gogo.model.Place;
import com.example.gogo.model.SampleData;
import com.example.gogo.repository.PlacesRepository;

import java.util.List;

public class PlacesListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PlacesRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_places);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.recycler_places);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        repository = new PlacesRepository(this);
        loadPlaces();
    }

    private void loadPlaces() {
        repository.getPlaces(new PlacesRepository.OnPlacesLoadedCallback() {
            @Override
            public void onLoaded(List<Place> places) {
                runOnUiThread(() -> {
                    if (places.isEmpty()) {
                        showSampleData();
                    } else {
                        recyclerView.setAdapter(new PlaceAdapter(PlacesListActivity.this, places));
                    }
                });
            }

            @Override
            public void onError(String message) {
                runOnUiThread(PlacesListActivity.this::showSampleData);
            }
        });
    }

    private void showSampleData() {
        List<Place> samplePlaces = SampleData.getSamplePlaces();
        recyclerView.setAdapter(new PlaceAdapter(this, samplePlaces));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
