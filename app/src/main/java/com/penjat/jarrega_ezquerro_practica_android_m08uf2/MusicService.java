package com.penjat.jarrega_ezquerro_practica_android_m08uf2;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MusicService extends IntentService {

    private MediaPlayer mp;


    public MusicService() {
        super("Servei Audio");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(this, R.raw.music);
        mp.setLooping(true);
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
                case "inici" : mp.start();
                    break;
                case "pausa" : mp.pause();
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
