package com.penjat.jarrega_ezquerro_practica_android_m08uf2;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HangedActivity extends AppCompatActivity {

    Button afegirLletra;
    TextInputEditText txtlletra;
    DatabaseReference DBHangedMan;
    DatabaseReference BDHangedManWords;
    DatabaseReference BDHangedManQuestions;

    ArrayList<Word> alw;
    ArrayList<Question> alq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hanged);

        DBHangedMan = FirebaseDatabase.getInstance().getReference("HangedMan");
        BDHangedManWords = DBHangedMan.child("Words");
        BDHangedManQuestions = DBHangedMan.child("Questions");

        txtlletra = findViewById(R.id.tieLletra);

        afegirLletra = findViewById(R.id.btAdd);

        alw = new ArrayList<Word>();
        alq = new ArrayList<Question>();

        afegirLletra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(alw.size());
                System.out.println(alq.size());
                comprovacioLletra();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        BDHangedManWords.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                alw.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String id = dataSnapshot.getKey();
                    String szWord = dataSnapshot.getValue(String.class);
                    Word w = new Word(id, szWord);
                    alw.add(w);
                    //System.out.println(dataSnapshot);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        BDHangedManQuestions.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                alw.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String id = dataSnapshot.getKey();
                    String szQuestion = dataSnapshot.getValue(String.class);
                    Question q = new Question(id, szQuestion);
                    alq.add(q);
                    //System.out.println(dataSnapshot);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void comprovacioLletra(){
        String letter = this.txtlletra.getText().toString().trim();
        char[] ch;

        if (!TextUtils.isEmpty(letter)){

            for (int i = 0; i < alw.size(); i++) {
                ch = alw.get(i).getWord().toCharArray();
                if (String.valueOf(ch[i]).equals(letter)){
                    System.out.println("hola");
                }
                System.out.println(ch);
            }
        }
    }
}
