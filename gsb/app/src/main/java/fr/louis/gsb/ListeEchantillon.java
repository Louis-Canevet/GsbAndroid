package fr.louis.gsb;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import fr.louis.gsb.outils.BdAdapter;

public class ListeEchantillon extends AppCompatActivity {

    private Button btnQuitterListe;
    private ListView listViewEchant;
    private BdAdapter echantBdd; // appel la class bdAdapter
    private SimpleCursorAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_echantillon);

        listViewEchant = findViewById(R.id.listeListeViewEchant);
        btnQuitterListe = findViewById(R.id.btnQuitterListe);
        echantBdd = new BdAdapter(this);

        // On ouvre la base de données pour écrire dedans
        echantBdd.open();

        Cursor leCurseur = echantBdd.getData();

        if (leCurseur.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "Aucun échantillon dans la base de données", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Il y a " + String.valueOf(leCurseur.getCount()) + " échantillons dans la base de données", Toast.LENGTH_LONG).show();

            // Colonnes à afficher
            String[] colNoms = new String[] { BdAdapter.COL_CODE, BdAdapter.COL_LABEL, BdAdapter.COL_STOCK };

            // Champs dans lesquels afficher les colonnes
            int[] colNumeros = new int[] { R.id.tvCodeEchantillon, R.id.tvLabelleEchantillon, R.id.tvStockEchantillon };

            dataAdapter = new SimpleCursorAdapter(this, R.layout.echant_layout, leCurseur, colNoms, colNumeros, 0);

            // Assigner l'adaptateur à la ListView
            listViewEchant.setAdapter(dataAdapter);
        }

        // Fermer la base de données
        echantBdd.close();

        // Ajouter un listener pour le bouton quitter
        btnQuitterListe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent quitterListEchantillon = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(quitterListEchantillon);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // On ouvre la base de données pour écrire dedans
        echantBdd.open();

        Cursor leCurseur = echantBdd.getData();

        if (leCurseur.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "Aucun échantillon dans la base de données", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Il y a " + String.valueOf(leCurseur.getCount()) + " échantillons dans la base de données", Toast.LENGTH_LONG).show();

            // Mettre à jour le curseur de l'adaptateur
            dataAdapter.changeCursor(leCurseur);
        }

        // Fermer la base de données
        echantBdd.close();
    }


}
