package com.example.rima.health_consultant;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements LocationListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    double latitude = 0;
    double longitude = 0;

    private static final String GOOGLE_API_KEY = "AIzaSyDeYG1vaxmHhW7szlhEgXZ73lXvU_BmQAM";
    GoogleMap googleMap;

    Context context;

    double[] Hospital_Latitudes = {22.8576105,22.8080281,22.900087,22.8107776,22.8312232,22.8080232,23.1667233,22.8118599,23.1657577,22.8175609};
    double[] Hospital_Longitudes = {89.467548,89.5683052,89.4983284,89.55988441,89.5414053,89.5704939,89.2093867,89.5614436,89.2115139,89.5455354};
    String[] Hospital_name={"Khulna Shishu hospital","khulna medical hospital","Kuet medical center","Khulna Diagonstic center","Khulna Diabetic center","B.A.V.S Hospitals, Khulna, Bangladesch","Central Hospital Jessore","Islami Bank Hospital, Khan Jahan Ali Road, Khulna","City Medical, M K Road, Jessore, Khulna","Gazi Medical College, Majid Sarani, Khulna"};

     //22.8576105,89.467548//khulna shishu hospital

    //22.8080281,89.5683052
    //22.9000871,89.4983284//kuet medical center
    //22.8291194,89.5349264//khulna medical hospital
    //22.8152728,89.5581779
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_places);
        setUpMapIfNeeded();
        context = this;

        //show error dialog if GoolglePlayServices not available
        if (!isGooglePlayServicesAvailable()) {
            //finish();
            Toast.makeText(this, "Service not available.", Toast.LENGTH_LONG);
            return;
        }

       // placeText = (EditText) findViewById(R.id.placeText);
        Button btnFind = (Button) findViewById(R.id.btnFind);
        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        googleMap = fragment.getMap();
        googleMap.setMyLocationEnabled(true);
        //LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //Criteria criteria = new Criteria();
        //String bestProvider = locationManager.getBestProvider(criteria, true);
        //Location location = locationManager.getLastKnownLocation(bestProvider);
        //if (location != null) {
        //    onLocationChanged(location);
        //}
        //locationManager.requestLocationUpdates(bestProvider, 20000, 0, this);
        googleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                onLocationChanged(location);
            }
        });


        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                String type = placeText.getText().toString();
                StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                googlePlacesUrl.append("location=" + type);
                //googlePlacesUrl.append("&radius=" + PROXIMITY_RADIUS);

                googlePlacesUrl.append("&sensor=true");
                googlePlacesUrl.append("&key=" + GOOGLE_API_KEY);

                GooglePlacesReadTask googlePlacesReadTask = new GooglePlacesReadTask();
                Object[] toPass = new Object[2];
                toPass[0] = googleMap;
                toPass[1] = googlePlacesUrl.toString();
                googlePlacesReadTask.execute(toPass);
                */


                // Getting user input location

               // if (location.equals("atm") == true)
                //{
                   // Toast.makeText(MapsActivity.this, "ATM", Toast.LENGTH_SHORT);
                    int I;
                    for (I = 0; I < Hospital_Latitudes.length; I++) {
                        LatLng L = new LatLng(Hospital_Latitudes[I], Hospital_Longitudes[I]);
                        googleMap.addMarker(new MarkerOptions().position(L).title(Hospital_name[I]));
                    }
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 10));
               // }


            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));

    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        //Toast.makeText(MapsActivity.this, String.valueOf(latitude), Toast.LENGTH_SHORT).show();
        //LatLng latLng = new LatLng(latitude, longitude);
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
       // mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }
}
