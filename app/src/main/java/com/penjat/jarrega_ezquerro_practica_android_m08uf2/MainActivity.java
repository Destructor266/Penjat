package com.penjat.jarrega_ezquerro_practica_android_m08uf2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Barra acció
 * menú
 * pantalla de benvinguda
 * Sistema de pistes (seqüencia de preguntes) Firebase
 * Musica de fons (opció de parar-la)
 * Sons de encert i error
 * Bones practiques de programacio multimedia (sons simultanis)
 * Animacions amb descriptors XML
 * Vista personalitzada i canvas
 * Serveis (IntentService i subclasses Service)
 * Propis fils (gestio basant-se amb prioritat)
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private CustomCanvas cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button btJugar;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cc = findViewById(R.id.vista);
        btJugar = findViewById(R.id.btPlay);
        btJugar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, HangedActivity.class);
        startActivity(intent);
    }
}