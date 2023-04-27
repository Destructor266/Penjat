package com.penjat.jarrega_ezquerro_practica_android_m08uf2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HangedActivity extends AppCompatActivity {

    Button afegirLletra;
    DatabaseReference DBHangedMan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hanged);

        DBHangedMan = FirebaseDatabase.getInstance().getReference("HangedMan");

        afegirLletra = findViewById(R.id.btAdd);

        afegirLletra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
