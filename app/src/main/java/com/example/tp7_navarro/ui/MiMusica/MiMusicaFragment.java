package com.example.tp7_navarro.ui.MiMusica;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.tp7_navarro.databinding.FragmentMimusicaBinding;

public class MiMusicaFragment extends Fragment {

    private FragmentMimusicaBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMimusicaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.BtMusica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getActivity(),MiMusicaViewModel.class);
               getActivity().startService(intent);

            }
        });



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}