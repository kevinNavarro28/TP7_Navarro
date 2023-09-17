package com.example.tp7_navarro.ui.Ubicacion;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;



    public class UbicacionViewModel extends AndroidViewModel {

        private Context context;
        private FusedLocationProviderClient fused;
        private MutableLiveData<MapaActual> mLocation;

        private MutableLiveData<Location> mLoc;



        private Location loca;

        public UbicacionViewModel(@NonNull Application application) {
            super(application);
            this.context = application;

            fused = LocationServices.getFusedLocationProviderClient(context);
        }
        public LiveData<Location> getMLoc() {
            if (mLoc == null) {
                mLoc = new MutableLiveData<>();
            }
            return mLoc;
        }

        public LiveData<MapaActual> getMLocation() {
            if (mLocation == null) {
                mLocation = new MutableLiveData<>();
            }
            return mLocation;
        }


        public void ultimaUbi() {

            if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            Task<Location> tarea = fused.getLastLocation();
            tarea.addOnSuccessListener(getApplication().getMainExecutor(), new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {

                    if (location != null) {
                        loca=location;



                    }

                }
            });

        }

        public void obtenerMapa(){
            MapaActual ma=new MapaActual();
            mLocation.setValue(ma);
        }


        public class MapaActual implements OnMapReadyCallback {


            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {

                if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                Task<Location> tarea = fused.getLastLocation();
                tarea.addOnSuccessListener(getApplication().getMainExecutor(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if (location != null) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                            LatLng latLng = new LatLng(latitude, longitude);
                            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

                            googleMap.addMarker(new MarkerOptions().position(latLng).title("Mi Ubicacion"));

                            CameraPosition camPos=new CameraPosition.Builder()
                                    .target(latLng)//donde aparecera
                                    .zoom(19)
                                    .bearing(45)//inclinacion
                                    .tilt(70)
                                    .build();// lo crea
                            CameraUpdate update= CameraUpdateFactory.newCameraPosition(camPos);
                            googleMap.animateCamera(update);


                        }

                    }
                });



            }
        }


    }