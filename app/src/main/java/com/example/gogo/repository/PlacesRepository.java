package com.example.gogo.repository;

import android.content.Context;

import com.example.gogo.db.AppDatabase;
import com.example.gogo.db.PlaceDao;
import com.example.gogo.model.Place;
import com.example.gogo.model.PlaceEntity;
import com.example.gogo.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository that fetches places from the network and caches them locally with Room.
 * <p>
 * <b>Threading:</b> {@link OnPlacesLoadedCallback} methods may be invoked on either the
 * main thread (network path via Retrofit's OkHttp dispatcher) or a background thread
 * (cache path). Callers must wrap UI updates in {@code runOnUiThread()} as needed.
 */
public class PlacesRepository {

    private final PlaceDao placeDao;
    private final RetrofitClient retrofitClient;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public interface OnPlacesLoadedCallback {
        void onLoaded(List<Place> places);
        void onError(String message);
    }

    public PlacesRepository(Context context) {
        placeDao = AppDatabase.getInstance(context).placeDao();
        retrofitClient = RetrofitClient.getInstance();
    }

    public void getPlaces(OnPlacesLoadedCallback callback) {
        retrofitClient.getApiService().getPlaces().enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Place> places = response.body();
                    cacheToDatabase(places);
                    callback.onLoaded(places);
                } else {
                    loadFromCache(callback);
                }
            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {
                loadFromCache(callback);
            }
        });
    }

    private void cacheToDatabase(List<Place> places) {
        executor.execute(() -> {
            List<PlaceEntity> entities = new ArrayList<>();
            for (Place p : places) {
                entities.add(PlaceEntity.fromPlace(p));
            }
            placeDao.deleteAll();
            placeDao.insertAll(entities);
        });
    }

    private void loadFromCache(OnPlacesLoadedCallback callback) {
        executor.execute(() -> {
            List<PlaceEntity> entities = placeDao.getAll();
            List<Place> places = new ArrayList<>();
            for (PlaceEntity e : entities) {
                places.add(e.toPlace());
            }
            callback.onLoaded(places);
        });
    }
}
