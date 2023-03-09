package fr.louis.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

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

        txtListViewEchantillon = findViewById(R.id.txtListViewEchantillon);
        txtTitreViewListeEchantillon = findViewById(R.id.txtTitreViewListeEchantillon);

        try {
            FileInputStream fileIn = new FileInputStream(getFilesDir() + "/EchantillonAjout.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Echantillon echantillonAjout = (EchantillonAjout) in.readObject();
            in.close();
            fileIn.close();

            // affichage des données
            donnees = "Code echantillon: " + echantillonAjout.getCode() + "\n" +
                    "Label echantillon: " + echantillonAjout.getLabel() + "\n" +
                    "Stock: " + echantillonAjout.getStock();

            txtListViewEchantillon.setText(donnees);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        btnQuitterListe = findViewById(R.id.btnQuitterListe);
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