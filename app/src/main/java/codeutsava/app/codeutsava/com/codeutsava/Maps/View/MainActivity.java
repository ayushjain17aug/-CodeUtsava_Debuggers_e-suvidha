package codeutsava.app.codeutsava.com.codeutsava.Maps.View;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import codeutsava.app.codeutsava.com.codeutsava.Maps.Model.Data.PositionInfo;
import codeutsava.app.codeutsava.com.codeutsava.Maps.Model.MockPositionProvider;
import codeutsava.app.codeutsava.com.codeutsava.Maps.Presenter.PositionPresenter;
import codeutsava.app.codeutsava.com.codeutsava.Maps.Presenter.PositionPresenterImpl;
import codeutsava.app.codeutsava.com.codeutsava.R;
import codeutsava.app.codeutsava.com.codeutsava.helper.LinearLayoutManagerWithSmoothScroller;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, PositionsView, PositionAdapter.ClickListener {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public PositionInfo position;
    double latitude;
    double longitude;
    GoogleApiClient mGoogleApiClient;
    ArrayList<Marker> allMarker;
    Location mLastLocation;
    MarkerOptions markerOptions = new MarkerOptions();
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    PositionAdapter positionAdapter;
    private GoogleMap mMap;
    private ArrayList<PositionInfo> positionInfos = new ArrayList<>();
    private ProgressBar progressBar;
    private int PROXIMITY_RADIUS = 10000;
    private boolean firstLoad = true;
    private boolean secondload = false;
    private RecyclerView recyclerView;
    private PositionPresenter positionPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        //show error dialog if Google Play Services not available
        if (!isGooglePlayServicesAvailable()) {
            Log.d("onCreate", "Google Play Services not available. Ending Test case.");
            finish();
        } else {
            Log.d("onCreate", "Google Play Services available. Continuing.");
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        positionPresenter = new PositionPresenterImpl(new MockPositionProvider(), this, this);

        allMarker = new ArrayList<>();
    }

    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        0).show();
            }
            return false;
        }
        return true;
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
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setMapToolbarEnabled(true);


        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

        Log.d("ayush", "entered");
        if (firstLoad) {
            mLastLocation = location;
            if (mCurrLocationMarker != null) {
                mCurrLocationMarker.remove();
            }
            //Place current location marker
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            positionPresenter.getPosition(latitude, longitude);
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.dual_marker));
            markerOptions.position(latLng);
            markerOptions.title("Current Position");

            // Adding colour to the marker
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));

            // Adding Marker to the Map
            mCurrLocationMarker = mMap.addMarker(markerOptions);

            //move map camera
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(12.0f));
        }firstLoad=false;
        Log.d("ayush", String.format("latitude:%.3f longitude:%.3f", latitude, longitude));

        Log.d("ayush", "Exit");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);

                    }


                } else {
                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }

    @Override
    public void showProgressBar(boolean b) {
        /* if (b)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE); */
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(findViewById(R.id.recycler_view), message, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    @Override
    public void showPosition(List<PositionInfo> data) {
        mMap.clear();

        for (int i = 0; i < data.size(); i++) {
            Double lat = data.get(i).getLatitude();
            Double lng = data.get(i).getLongitude();
            String name = data.get(i).getName();
            String address = data.get(i).getAddress();
            String rating = data.get(i).getRating();
            String hours = data.get(i).getHours();
            String flagf = data.get(i).getFlagf();
            String flagm = data.get(i).getFlagm();


            LatLng latLng = new LatLng(lat, lng);
            // Position of Marker on Map
            markerOptions.position(latLng);
            // Adding Title to the Marker
            markerOptions.title(name);
            // Adding Marker to the Camera.
            Marker m = mMap.addMarker(markerOptions);
            allMarker.add(m);

            // Adding colour to the marker
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            // move map camera
           /* mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16.0f));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(16.0f)); */
            position = new PositionInfo(lat, lng, name, address, rating, hours, flagf, flagm);
            positionInfos.add(position);
        }
        setupRecyclerView(1);
    }

    private void setupRecyclerView(int chosenTheme) {
        // Initialize the recyclerview of location cards and a custom class for automatic card scrolling
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManagerWithSmoothScroller(this));
        positionAdapter = new PositionAdapter(positionInfos,
                getApplicationContext(), this, chosenTheme);
        recyclerView.setAdapter(positionAdapter);
        recyclerView.setOnFlingListener(null);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onItemClick(int position) {
        PositionInfo positionInfo = positionInfos.get(position);

        // Retrieve and change the selected card's marker to the selected marker icon
        Marker markerTiedToSelectedCard = allMarker.get(position);
        adjustMarkerSelectStateIcons(markerTiedToSelectedCard);

        // Reposition the map camera target to the selected marker
        LatLng selectedLocationLatLng = new LatLng(positionInfo.getLatitude(), positionInfo.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(selectedLocationLatLng, 12.0f));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12.0f));

    }

    private void adjustMarkerSelectStateIcons(Marker marker) {
        // Set all of the markers' icons to the unselected marker icon
        /*for (Marker singleMarker : allMarker.getMarkers()) {
            if (!singleMarker.getTitle().equals(getString(R.string.mock_location_title))) {
                singleMarker.setIcon(customThemeManager.getUnselectedMarkerIcon());
            }
        }

        // Change the selected marker's icon to a selected state marker except if the mock device location marker is selected
        if (!marker.getIcon().equals(customThemeManager.getMockLocationIcon())) {
            marker.setIcon(customThemeManager.getSelectedMarkerIcon());
        }

        // Get the directionsApiClient route to the selected marker except if the mock device location marker is selected
        if (!marker.getIcon().equals(customThemeManager.getMockLocationIcon())) {
            // Check for an internet connection before making the call to Mapbox Directions API
            if (deviceHasInternetConnection()) {
                // Start the call to the Mapbox Directions API
                getInformationFromDirectionsApi(marker.getPosition().getLatitude(),
                        marker.getPosition().getLongitude(), true, null);
            } else {
                Toast.makeText(this, R.string.no_internet_message, Toast.LENGTH_LONG).show();
            }
        }*/
    }
}
