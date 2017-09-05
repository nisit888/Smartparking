package com.user.smartparking;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Toast;
import android.app.IntentService;
import android.support.v4.app.TaskStackBuilder;
import android.app.PendingIntent;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.GeofenceStatusCodes;

import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener {

    private GoogleMap mMap;
    private Marker mBuild7;
    private Marker mCentral;
    private Marker mBuild6;
    private Marker coeLatLng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    // More details: https://developers.google.com/maps/documentation/android-api/marker
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in Sydney and move the camera
        LatLng coeLatLng = new LatLng(7.893444, 98.352325);
        mBuild6 = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(7.893444, 98.352325))
                .snippet("Department of Computer Engineering, Prince of Songkla University, Phuket Campus")
//                .icon(BitmapDescriptorFactory      // replace default icon marker
//                        .fromResource(R.drawable.logobuilding6))
                .title("Parking of Building 6 "));
        mBuild6.setTag(0);

        mBuild7 = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(7.894406, 98.352836))
                .snippet("Building 7 ")
                //.icon(BitmapDescriptorFactory      // replace default icon marker
                //        .fromResource(R.drawable.logobuilding7))
                .title("Parking of Building 7"));
        mBuild7.setTag(0);

        mCentral = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(7.892974, 98.351891))
                .snippet("Canteen")
//                .icon(BitmapDescriptorFactory   // replace icon color
//                        .fromResource(R.drawable.logocanteen))
                .title("Parking of Canteen"));
        mCentral.setTag(0);
        mCentral.setDraggable(true);

        mMap.setOnMarkerClickListener(this);
        mMap.setOnMarkerDragListener(this);


        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(coeLatLng).zoom(18).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();
//
        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();

        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Intent x = new Intent(MapsActivity.this, MainActivity.class);
            startActivity(x);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onMarkerDragStart(Marker marker) {
        Toast.makeText(this, marker.getTitle() + " is starting dragged ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        System.out.println(marker.getTitle() + " is dragged ");
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        LatLng position = marker.getPosition();
        Toast.makeText(this, marker.getTitle() + " is ending dragged at " + position, Toast.LENGTH_SHORT).show();
    }

}

