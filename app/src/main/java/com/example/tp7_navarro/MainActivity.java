package com.example.tp7_navarro;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp7_navarro.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel mv;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mv= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);


        binding.BtIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.ingresar(binding.EtEmail.getText().toString(),binding.EtPassword.getText().toString());

            }

        });
        mv.getMAle().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvAlerta.setText(s);
            }
        });

    }


}