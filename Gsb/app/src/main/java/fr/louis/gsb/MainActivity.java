package fr.louis.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // propriété
    private TextView txtPresentation;
    private Button btnAddEnchentillon;
    private Button btnListeEchantillon;
    private Button btnMajEchantillon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * initialisation des liens avec les objets graphiques
         */
        txtPresentation = (TextView) findViewById(R.id.txtPresentation);
        btnAddEnchentillon = (Button) findViewById(R.id.btnAddEnchentillon);
        btnListeEchantillon = (Button) findViewById(R.id.btnListeEchantillon);
        btnMajEchantillon = (Button) findViewById(R.id.btnMajEchantillon);


        this.btnAddEnchentillon = (Button) findViewById(R.id.btnAddEnchentillon);
        btnAddEnchentillon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ajoutEchantillon = new Intent(MainActivity.this, AjoutEchantillon.class);
                startActivity(ajoutEchantillon);
                finish();
            }
        });


        this.btnListeEchantillon = (Button) findViewById(R.id.btnListeEchantillon);
        btnListeEchantillon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listeEchantillons = new Intent(getApplicationContext(), ListeEchantillon.class);
                startActivity(listeEchantillons);
                finish();
            }
        });


        this.btnMajEchantillon = (Button) findViewById(R.id.btnMajEchantillon);
        btnMajEchantillon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent majEchantillon = new Intent(getApplicationContext(), MajEchantillon.class);
                startActivity(majEchantillon);
                finish();
            }
        });


    }
}