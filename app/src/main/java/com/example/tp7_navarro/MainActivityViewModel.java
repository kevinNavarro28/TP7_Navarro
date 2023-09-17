package com.example.tp7_navarro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;

    private MutableLiveData<String> mAle;



    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context= application.getApplicationContext();
        }
    public LiveData<String> getMAle() {

        if (mAle == null) {

            mAle = new MutableLiveData<String>();
        }
        return mAle;
    }
        public void ingresar(String email,String pass){

        if(email.equals("a")&& pass.equals("1")){
            Intent intent= new Intent(context, MenuActivity.class);
            intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        else{
            mAle.setValue("Email o password incorrectos");

        }
    }
}
