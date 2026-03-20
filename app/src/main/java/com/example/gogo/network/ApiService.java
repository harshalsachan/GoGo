package com.example.gogo.network;

import com.example.gogo.model.Place;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("places")
    Call<List<Place>> getPlaces();
}
