package com.penjat.jarrega_ezquerro_practica_android_m08uf2;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class HangedActivity extends AppCompatActivity {

    private Button afegirLletra;
    private FloatingActionButton fab;
    private TextInputEditText txtlletra;
    private TextView txtQuestion;
    private ImageView imageView;

    private AnimationDrawable animationDrawable;
    private Intent intentM, intentSFX;
    private DatabaseReference DBHangedMan;
    private DatabaseReference BDHangedManWords;
    private DatabaseReference BDHangedManQuestions;

    private ArrayList<Word> alw;
    private ArrayList<Question> alq;
    private ArrayList<TextView> letters;

    private Random random;

    private String word;
    private String question;
    private boolean isReproduint = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hanged);

        intentM = new Intent(this, MusicService.class);
        intentM.putExtra("operacio", "inici");
        startService(intentM);

        intentSFX = new Intent(this, SFXService.class);

        DBHangedMan = FirebaseDatabase.getInstance().getReference("HangedMan");
        BDHangedManWords = DBHangedMan.child("Words");
        BDHangedManQuestions = DBHangedMan.child("Questions");

        imageView = findViewById(R.id.imageView);
        txtlletra = findViewById(R.id.tieLletra);
        afegirLletra = findViewById(R.id.btAdd);
        fab = findViewById(R.id.floatingActionButton);
        fab.setImageResource(android.R.drawable.ic_media_pause);
        imageView.setBackgroundResource(R.drawable.sprite);

        alw = new ArrayList<Word>();
        alq = new ArrayList<Question>();
        letters = new ArrayList<TextView>();

        letters.add(findViewById(R.id.tvLetter1));
        letters.add(findViewById(R.id.tvLetter2));
        letters.add(findViewById(R.id.tvLetter3));
        letters.add(findViewById(R.id.tvLetter4));
        letters.add(findViewById(R.id.tvLetter5));

        random = new Random();

        animationDrawable = (AnimationDrawable) imageView.getBackground();


        afegirLletra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(alw.size());
                System.out.println(alq.size());
                comprovacioLletra(word);
                //animationDrawable.start();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isReproduint){
                    isReproduint = true;
                    fab.setImageResource(android.R.drawable.ic_media_pause);
                    intentM.putExtra("operacio", "inici");
                    startService(intentM);
                } else {
                    isReproduint = false;
                    fab.setImageResource(android.R.drawable.ic_media_play);
                    intentM.putExtra("operacio", "pausa");
                    startService(intentM);
                }
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
                word = SelectWord(random);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        BDHangedManQuestions.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                alq.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
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
    private String SelectWord(Random random){
        int id = Integer.parseInt(String.valueOf(alw.get(random.nextInt(4)).getId()
                .charAt(alw.get(random.nextInt(4)).getId().length() - 1)));
        question = SelectQuestion(id);
        PrintQuestion(question);
        return alw.get(id).getWord();
    }

    private String SelectQuestion(int idW){
        return alq.get(idW).getQuestion();
    }

    private void comprovacioLletra(String word){
        String letter = this.txtlletra.getText().toString().trim();
        char[] ch = word.toCharArray();
        boolean isCorrect = false;
        int frame = 0;

        if (!TextUtils.isEmpty(letter)){
            for (int i = 0; i < ch.length; i++) {
                if (String.valueOf(ch[i]).equalsIgnoreCase(letter)){
                    letters.get(i).setText(letter);
                    isCorrect = true;
                }
            }
        }

        if (isCorrect){
            intentSFX.putExtra("operacio", "correct");
            startService(intentSFX);
        }else {
            frame++;
            intentSFX.putExtra("operacio", "error");
            startService(intentSFX);
            if (frame <= animationDrawable.getNumberOfFrames()){
                animationDrawable.selectDrawable(frame);
            }
        }

        System.out.println("Selected word" + Arrays.toString(ch));
    }

    private void PrintQuestion(String Question){
        txtQuestion = findViewById(R.id.tvQuestion);
        txtQuestion.setText(Question);
    }
}
