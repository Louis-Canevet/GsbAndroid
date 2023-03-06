package fr.louis.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import fr.louis.gsb.R;

public class ListeEchantillon extends AppCompatActivity {

    // Propriétés
    private String donnees;
    private TextView txtListViewEchantillon;
    private TextView txtTitreViewListeEchantillon;
    private Button btnQuitterListe;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_echantillon);

        txtListViewEchantillon = (TextView) findViewById(R.id.txtListViewEchantillon);
        txtTitreViewListeEchantillon = (TextView) findViewById(R.id.txtTitreViewListeEchantillon);






        donnees = donnees+" / ";



        this.btnQuitterListe = (Button) findViewById(R.id.btnQuitterListe);
        btnQuitterListe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent quitterListEchantillon = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(quitterListEchantillon);
                finish();
            }
        });
    }
}