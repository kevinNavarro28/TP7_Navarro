package com.example.tp7_navarro.ui.Salir;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Salir {


    public static void salirDialog(Activity activity){
        new AlertDialog.Builder(activity)
                .setTitle("Cerrar Menu")
                .setMessage("cerrar ?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(activity,"continuamos trabajando",Toast.LENGTH_LONG).show();
                    }
                }).show();
    }
}