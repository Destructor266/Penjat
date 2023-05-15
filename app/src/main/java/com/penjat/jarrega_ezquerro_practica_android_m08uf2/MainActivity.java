package com.penjat.jarrega_ezquerro_practica_android_m08uf2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ajuda:
                obrirAjuda();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Mostra el component webview amb una explicacio de l'aplicacio.
     */

    private void obrirAjuda() {
        setContentView(R.layout.webview);
        WebView navegador = (WebView) findViewById(R.id.webkit);

        navegador.setWebViewClient(new WebViewClient());
        navegador.loadData("<html>" +
                "                   <body style=\"background-color:orange;\">" +
                "                       <h1 style=\"text-align: center;\">Welcome to the HangedMan</h1>" +
                "                       <p>In this page i will explain how this game works<p>" +
                "                       <p>You've gotta know the letters of a word that is provided by the database using some hints questions</p>" +
                "                       <p>The game has 10 tries if you spend them all trying to know the letters you lose</p>" +
                "                   </body>" +
                "                </html>", "text/html", "UTF-8");
    }

}