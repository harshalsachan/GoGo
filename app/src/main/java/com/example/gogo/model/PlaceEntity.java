package com.example.gogo.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "places")
public class PlaceEntity {

    @PrimaryKey
    private int id;
    private String name;
    private String description;
    private float rating;
    private String imageUrl;
    private double latitude;
    private double longitude;
    private String address;

    public PlaceEntity(int id, String name, String description, float rating,
                       String imageUrl, double latitude, double longitude, String address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public static PlaceEntity fromPlace(Place place) {
        return new PlaceEntity(place.getId(), place.getName(), place.getDescription(),
                place.getRating(), place.getImageUrl(), place.getLatitude(),
                place.getLongitude(), place.getAddress());
    }

    public Place toPlace() {
        return new Place(id, name, description, rating, imageUrl, latitude, longitude, address);
    }
}
