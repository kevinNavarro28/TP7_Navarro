package com.example.tp7_navarro.ui.MiMusica;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp7_navarro.R;

import java.security.Provider;

public class MiMusicaViewModel extends Service {

    private MediaPlayer mp;

    public MiMusicaViewModel() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mp =MediaPlayer.create(this, R.raw.soda_stereo_tratame_suavemente);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mp.start();
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.stop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}