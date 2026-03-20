# GoGo — Tourism App

GoGo is an Android tourism app built with Java and the Groovy Gradle DSL. It lets users explore tourist attractions on a map, browse a list of places, and view detailed information including ratings and directions.

## Features

- **Map Screen** — Interactive Google Map (`SupportMapFragment`) with markers for popular tourist spots. Tap a marker info-window to open the detail screen.
- **Places List** — Scrollable `RecyclerView` with place cards. Images are loaded with [Glide](https://github.com/bumptech/glide).
- **Place Detail** — Full description, star rating, address, and an **Open in Maps** button that launches Google Maps (or falls back to a browser).
- **Offline Cache** — Places fetched from the API are persisted locally with [Room](https://developer.android.com/training/data-storage/room). The app falls back to the cache when offline.
- **Networking** — REST calls via [Retrofit 2](https://square.github.io/retrofit/) with a Gson converter.

## Project Structure

```
app/src/main/java/com/example/gogo/
├── MainActivity.java               # Launch screen with navigation buttons
├── MapActivity.java                # Google Maps screen
├── PlacesListActivity.java         # RecyclerView list of places
├── PlaceDetailActivity.java        # Detail screen with ratings & maps intent
├── PlaceAdapter.java               # RecyclerView adapter (uses Glide)
│
├── model/
│   ├── Place.java                  # Network / domain model (Gson annotations)
│   ├── PlaceEntity.java            # Room entity
│   └── SampleData.java             # Hard-coded sample places for demo/offline
│
├── db/
│   ├── PlaceDao.java               # Room DAO
│   └── AppDatabase.java            # Room database singleton
│
├── network/
│   ├── ApiService.java             # Retrofit interface
│   └── RetrofitClient.java         # Retrofit singleton
│
└── repository/
    └── PlacesRepository.java       # Combines network + Room cache
```

## Setup

### 1. Clone the repository

```bash
git clone https://github.com/harshalsachan/GoGo.git
cd GoGo
```

### 2. Configure a Google Maps API Key

1. Go to the [Google Cloud Console](https://console.cloud.google.com/) and create (or select) a project.
2. Enable the **Maps SDK for Android**.
3. Create an API key and restrict it to your app's package name (`com.example.gogo`) and SHA-1 certificate fingerprint.
4. Open `app/src/main/AndroidManifest.xml` and replace the placeholder value:

```xml
<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="YOUR_MAPS_API_KEY" />
```

> **Never commit a real API key to version control.** Consider storing it in `local.properties` and reading it via `manifestPlaceholders` in `build.gradle`.

### 3. Configure the API base URL (optional)

Open `app/src/main/java/com/example/gogo/network/RetrofitClient.java` and replace:

```java
private static final String BASE_URL = "https://api.example.com/v1/";
```

with your actual API endpoint. The app falls back to the bundled `SampleData` when the network is unavailable or the endpoint is not configured.

### 4. Build and run

```bash
# Debug APK
./gradlew :app:assembleDebug

# Install on connected device / emulator
./gradlew :app:installDebug
```

Or open the project in **Android Studio** and press **Run**.

## Version Control Tips

- **Do commit:** `gradle/wrapper/gradle-wrapper.properties`, `gradle/libs.versions.toml`, `app/build.gradle`, source files, and resource files.
- **Do not commit:** `local.properties` (contains your SDK path and may hold secrets). It is already listed in `.gitignore`.
- **Do not commit:** `build/` directories or `*.apk` files.

## Dependencies

| Library | Version | Purpose |
|---------|---------|---------|
| Google Maps SDK | 19.0.0 | Interactive map with `SupportMapFragment` |
| Retrofit 2 | 2.9.0 | HTTP networking |
| Gson Converter | 2.9.0 | JSON ↔ Java model conversion |
| Glide | 4.16.0 | Image loading & caching |
| Room | 2.6.1 | Offline SQLite persistence |
| RecyclerView | 1.3.2 | Scrollable list of places |
