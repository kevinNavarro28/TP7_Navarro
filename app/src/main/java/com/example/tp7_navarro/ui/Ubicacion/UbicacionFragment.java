package com.example.tp7_navarro.ui.Ubicacion;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import static androidx.core.content.PermissionChecker.checkSelfPermission;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.example.tp7_navarro.R;
import com.example.tp7_navarro.databinding.FragmentUbicacionBinding;
import com.google.android.gms.maps.SupportMapFragment;

public class UbicacionFragment extends Fragment {

    private FragmentUbicacionBinding binding;
    private UbicacionViewModel mv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UbicacionViewModel UbicacionViewModel =
               mv = new ViewModelProvider(this).get(UbicacionViewModel.class);

        binding = FragmentUbicacionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    && (requireContext().checkSelfPermission(ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) ||
                    (requireContext().checkSelfPermission(ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
                requestPermissions(new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, 1000);
            }


        mv.getMLocation().observe(getViewLifecycleOwner(), new Observer<com.example.tp7_navarro.ui.Ubicacion.UbicacionViewModel.MapaActual>() {
            @Override
            public void onChanged(com.example.tp7_navarro.ui.Ubicacion.UbicacionViewModel.MapaActual mapaActual) {
                SupportMapFragment smf=(SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
                smf.getMapAsync(mapaActual);
            }

        });
            mv.obtenerMapa();
            //mv.ultimaUbi();

            return root;
        }

        @Override
        public void onDestroyView () {
            super.onDestroyView();
            binding = null;
        }
    }
