package com.example.android;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.example.android.bluetoothchat.BluetoothChatFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener {

    //private Marker ElleP;
    private GoogleMap mMap;
    private BluetoothChatFragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.mapslayout);
        setContentView(com.example.android.R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            fragment = new BluetoothChatFragment();
            transaction.replace(R.id.sample_content_fragment, fragment, "chatfrag");
            transaction.commit();

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
        mMap.setOnMarkerClickListener(this);
        // Add a marker in Sydney and move the camera
        LatLng western = new LatLng(43.0155,-81.272);
        Marker ElleP = mMap.addMarker(new MarkerOptions().position(western).title("Marker at Western").draggable(true));
        ElleP.setTitle("Marker at" + ElleP.getPosition());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(western));
        mMap.setOnMarkerDragListener(this);

        mMap.setOnMapLongClickListener(this);

    }



    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        marker.setTitle("Marker at" + marker.getPosition());
        marker.showInfoWindow();
        String updatedposition= marker.getPosition().toString();
        Senddatabacktofragment(updatedposition);

    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(43.0155, -81.272)));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(14));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    public void Senddatabacktofragment(String pos){
        //Bundle posdata = new Bundle();
    //posdata.putString("updatedpos", pos);
       fragment.sendcoordinates(pos);

    }

}

