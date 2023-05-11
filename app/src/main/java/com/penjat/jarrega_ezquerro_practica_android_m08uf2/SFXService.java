package com.penjat.jarrega_ezquerro_practica_android_m08uf2;

import android.app.IntentService;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class SFXService extends IntentService {
    private MediaPlayer mpC;
    private MediaPlayer mpE;

    public SFXService() {
        super("SFXService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mpC = MediaPlayer.create(this, R.raw.correct);
        mpE = MediaPlayer.create(this, R.raw.error);
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        if (intent != null) {
            String operacio = intent.getStringExtra("operacio");
            switch (operacio){
                case "correct" : mpC.start();
                    break;
                case "error" : mpE.start();
                    break;
                default:
                    break;
            }
        }
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

}
