package com.ddg.project.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ddg.project.R;
import com.ddg.project.adapter.CustomInfoWindowAdapter;
import com.ddg.project.adapter.PlaceAutocompleteAdapter;
import com.ddg.project.helper.HelperClass;
import com.ddg.project.helper.HideKeyBoard;
import com.ddg.project.helper.PlaceInfo;
import com.ddg.project.helper.SharedPreferenceManager;
import com.ddg.project.model.SingleTone;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener {

    private static final int LOCATION_PERMISSION_RESUILT_CODE = 123;
    private GoogleMap mMap;
    private String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private boolean mLocatiomPermissionGranted = false;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private static final float DEFAULT_ZOOM = 10f;

    //widgets
    private AutoCompleteTextView mSearchText;
    private ImageView mGps, mInfo, mPlacePicker;
    private GoogleApiClient mGoogleApiClient;
    private PlaceAutocompleteAdapter mPlaceAutocompleteAdapter;
    private PlaceInfo mPlace;
    private Marker mMarker;
    private static final int PLACE_PICKER_REQUEST = 1;
    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(
            new LatLng(-40, -168), new LatLng(71, 136));
    private Place place;
    private MarkerOptions markerOptions;
    private String userAddressTitle;
    private LatLng userPosition;
    private SharedPreferenceManager sharedPreferenceManager;
    private String userAddressSnippet;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private String activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        sharedPreferenceManager = new SharedPreferenceManager(MapsActivity.this);
        mSearchText = (AutoCompleteTextView) findViewById(R.id.input_search);
        mInfo = findViewById(R.id.place_info);
        mPlacePicker = findViewById(R.id.place_picker);
        mGps = findViewById(R.id.ic_gps);
        markerOptions = new MarkerOptions();

        getLocationPermission();
        if (getIntent().getStringExtra("activity") != null) {
            activity = getIntent().getStringExtra("activity");
        }

    }

    private void initMap() {

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    private void getLocationPermission() {

        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                mLocatiomPermissionGranted = true;
                initMap();

            } else {


                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_RESUILT_CODE);
            }

        } else {


            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_RESUILT_CODE);
        }
    }

    private void getDeviceLocation() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
int x=0;

            try {

                if (mLocatiomPermissionGranted) {


                    Task location = mFusedLocationProviderClient.getLastLocation();

                    location.addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (task.isSuccessful()) {
                                Log.d(SingleTone.TAG, "onComplete: found current location");
                                Location currentLocation = (Location) task.getResult();

                                if (currentLocation != null) {
                                    double latitude = currentLocation.getLatitude();
                                    double longitude = currentLocation.getLongitude();
                                    LatLng latLng = new LatLng(latitude, longitude);
                                    moveCamera(latLng, DEFAULT_ZOOM);

                                }

                            } else {
                                Log.d(SingleTone.TAG, "onComplete: current location is null");

                                Toast.makeText(MapsActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
                }
            } catch (SecurityException e) {
                Log.d(SingleTone.TAG, "getDeviceLocation: SecurityException " + e.getMessage());
            }
        } else {

            HelperClass.showSettingsAlert(MapsActivity.this);
        }

        // end of checkLocationIsEnabled condition
    } // end of getDeviceLocation method

    private void moveCamera(LatLng latLng, float zoom) {


        mMap.clear();

        Geocoder geocoder = new Geocoder(MapsActivity.this);
        List<Address> list;
        try {
            list = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
        } catch (IOException e) {
            return;
        }

        if (list != null && list.size() > 0) {
            Address address = list.get(0);

            markerOptions.title(address.getAddressLine(0))
                    .snippet(address.getAddressLine(0) + address.getLocality() + address.getLocality())
                    .position(latLng);

            mMarker = mMap.addMarker(markerOptions);
            mMarker.showInfoWindow();
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));


            userAddressTitle = mMarker.getTitle();
            userAddressSnippet = address.getAddressLine(0);
            userPosition = mMarker.getPosition();


            HideKeyBoard.hideSoftKeyboard(MapsActivity.this);
            saveUserLocation();
        }

    }


    private void moveCamera(LatLng latLng, float zoom, String title) {
        Log.d(SingleTone.TAG, "moveCamera: moving the camera to lat:" + latLng.latitude + "long" + latLng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
        if (!title.equals("My Location")) {
            markerOptions.position(latLng)
                    .title(title);
            mMarker = mMap.addMarker(markerOptions);
            userAddressTitle = mMarker.getTitle();
            userAddressSnippet = mMarker.getSnippet();
            userPosition = mMarker.getPosition();
        }

        HideKeyBoard.hideSoftKeyboard(MapsActivity.this);


        saveUserLocation();

    }


    private void moveCamera(LatLng latLng, float zoom, PlaceInfo placeInfo) {
        Log.d(SingleTone.TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        mMap.clear();

        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(MapsActivity.this));

        if (placeInfo != null) {
            try {


                markerOptions.position(latLng)
                        .title(placeInfo.getName())
                        .snippet(placeInfo.getName() + "\n" + placeInfo.getAddress());
                mMarker = mMap.addMarker(markerOptions);
                mMarker.showInfoWindow();


                userAddressTitle = mMarker.getTitle();
                userAddressSnippet = placeInfo.getAddress();
                userPosition = mMarker.getPosition();
            } catch (NullPointerException e) {
                Log.e(SingleTone.TAG, "moveCamera: NullPointerException: " + e.getMessage());
            }
        } else {


            mMarker = mMap.addMarker(markerOptions.position(latLng));
            mMarker.showInfoWindow();

            userAddressTitle = mMarker.getTitle();
            userAddressSnippet = mMarker.getTitle() + mMarker.getSnippet();
            userPosition = mMarker.getPosition();

        }

        HideKeyBoard.hideSoftKeyboard(MapsActivity.this);
        saveUserLocation();

    }


    private void init() {


        Log.d(SingleTone.TAG, "init: initializing");

        mGoogleApiClient = new GoogleApiClient
                .Builder(MapsActivity.this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        //todo setOnMapClickListener
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {
                moveCamera(point, DEFAULT_ZOOM);


            }
        });


        mSearchText.setOnItemClickListener(mAutocompleteClickListener);

        mPlaceAutocompleteAdapter = new PlaceAutocompleteAdapter(this, mGoogleApiClient,
                LAT_LNG_BOUNDS, null);

        mSearchText.setAdapter(mPlaceAutocompleteAdapter);

        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER) {

                    //execute our method for searching
                    geoLocate();
                }

                return false;
            }


        });
        mGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(SingleTone.TAG, "onClick: clicked gps icon");
                getDeviceLocation();
            }
        });


        mInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(SingleTone.TAG, "onClick: clicked place info");
                try {
                    if (mMarker.isInfoWindowShown()) {
                        mMarker.hideInfoWindow();
                    } else {
                        Log.d(SingleTone.TAG, "onClick: place info: " + mPlace.toString());
                        mMarker.showInfoWindow();
                    }
                } catch (NullPointerException e) {
                    Log.e(SingleTone.TAG, "onClick: NullPointerException: " + e.getMessage());
                }
            }
        });

        mPlacePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                try {
                    startActivityForResult(builder.build(MapsActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    Log.e(SingleTone.TAG, "onClick: GooglePlayServicesRepairableException: " + e.getMessage());
                } catch (GooglePlayServicesNotAvailableException e) {
                    Log.e(SingleTone.TAG, "onClick: GooglePlayServicesNotAvailableException: " + e.getMessage());
                }
            }
        });

        HideKeyBoard.hideSoftKeyboard(MapsActivity.this);


    }

    private void geoLocate() {

        String searchString = mSearchText.getText().toString();

        Geocoder geocoder = new Geocoder(MapsActivity.this);
        List<Address> list = new ArrayList<>();
        try {
            list = geocoder.getFromLocationName(searchString, 1);
        } catch (IOException e) {
            Log.e(SingleTone.TAG, "geoLocate: IOException: " + e.getMessage());
        }

        if (list.size() > 0) {
            Address address = list.get(0);

            Log.d(SingleTone.TAG, "geoLocate: found a location: " + address.toString());
            //Toast.makeText(this, address.toString(), Toast.LENGTH_SHORT).show();

            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            moveCamera(latLng, DEFAULT_ZOOM, address.getAddressLine(0) + address.getLocality() + address.getLocality());


        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocatiomPermissionGranted = false;

        switch (requestCode) {
            case LOCATION_PERMISSION_RESUILT_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocatiomPermissionGranted = false;
                            return;
                        }
                    }
                    mLocatiomPermissionGranted = true;
                    //initialize our map
                    initMap();
                }
            }
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
//        Toast.makeText(this, "map is ready", Toast.LENGTH_SHORT).show();
        // Add a marker in Sydney and move the camera
      /*  LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/


        if (mLocatiomPermissionGranted) {

            getDeviceLocation();

//todo new location map
//            mapmap();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            init();


        }

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this, data);

                PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                        .getPlaceById(mGoogleApiClient, place.getId());
                placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
            }

            if (requestCode == 1) {


                getDeviceLocation();
//startActivity(new Intent(MapsActivity.this,MapsActivity.class));


            }
        }
    }

     /*
        --------------------------- google places API autocomplete suggestions -----------------
     */

    private AdapterView.OnItemClickListener mAutocompleteClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            HideKeyBoard.hideSoftKeyboard(MapsActivity.this);


            final AutocompletePrediction item = mPlaceAutocompleteAdapter.getItem(i);
            final String placeId = item.getPlaceId();

            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
        }
    };

    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(@NonNull PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                Log.d(SingleTone.TAG, "onResult: Place query did not complete successfully: " + places.getStatus().toString());
                places.release();
                return;
            }
            place = places.get(0);

            try {
                mPlace = new PlaceInfo();
                mPlace.setName(place.getName().toString());
                Log.d(SingleTone.TAG, "onResult: name: " + place.getName());
                mPlace.setAddress(place.getAddress().toString());
                Log.d(SingleTone.TAG, "onResult: address: " + place.getAddress());
//                mPlace.setAttributions(place.getAttributions().toString());
//                Log.d(TAG, "onResult: attributions: " + place.getAttributions());
                mPlace.setId(place.getId());
                Log.d(SingleTone.TAG, "onResult: id:" + place.getId());
                mPlace.setLatlng(place.getLatLng());
                Log.d(SingleTone.TAG, "onResult: latlng: " + place.getLatLng());
                mPlace.setRating(place.getRating());
                Log.d(SingleTone.TAG, "onResult: rating: " + place.getRating());
                mPlace.setPhoneNumber(place.getPhoneNumber().toString());
                Log.d(SingleTone.TAG, "onResult: phone number: " + place.getPhoneNumber());
                mPlace.setWebsiteUri(place.getWebsiteUri());
                Log.d(SingleTone.TAG, "onResult: website uri: " + place.getWebsiteUri());

                Log.d(SingleTone.TAG, "onResult: place: " + mPlace.toString());
            } catch (NullPointerException e) {
                Log.e(SingleTone.TAG, "onResult: NullPointerException: " + e.getMessage());
            }

            moveCamera(new LatLng(place.getViewport().getCenter().latitude,
                    place.getViewport().getCenter().longitude), DEFAULT_ZOOM, mPlace);

            places.release();
        }
    };

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {


    }


/*
    public void mapmap() {

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                mMap.clear();

                LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());

                mMap.addMarker(new MarkerOptions().position(userLocation).title("Marker"));

                mMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation));

                Toast.makeText(MapsActivity.this, userLocation.toString(), Toast.LENGTH_SHORT).show();
            }


        };
    }
*/

    private void saveUserLocation() {
        if (userAddressSnippet != null && userAddressSnippet != null && userPosition != null) {
            sharedPreferenceManager.saveUserCoordinates(userAddressSnippet, userAddressSnippet, userPosition);
        }

    }

    public void save(View view) {
        saveUserLocation();
        onBackPressed();


    }
}
