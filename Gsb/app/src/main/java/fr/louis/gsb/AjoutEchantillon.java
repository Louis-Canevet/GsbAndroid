package fr.louis.gsb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class AjoutEchantillon extends AppCompatActivity implements View.OnClickListener {

    // propriété
    private TextView txtSaisieEchantillon;
    private EditText editTextRef;
    private EditText editTextDesign;
    private EditText editTextPu;
    private Button btnAjouter;
    private Button btnQuitter;
    private TextView txtAffichage;
    private String donnees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_echantillon);

        txtSaisieEchantillon = findViewById(R.id.txtSaisieEchantillon);
        editTextRef = findViewById(R.id.editTextRef);
        editTextDesign = findViewById(R.id.editTextDesign);
        editTextPu = findViewById(R.id.editTextPu);
        btnAjouter = findViewById(R.id.btnAjouter);
        btnQuitter = findViewById(R.id.btnQuitter);
        txtAffichage = findViewById(R.id.txtAffichage);

        btnAjouter.setOnClickListener(this);
        donnees = "";
        btnQuitter.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view == btnAjouter) {
            String code = editTextRef.getText().toString();
            String label = editTextDesign.getText().toString();
            int stock = Integer.parseInt(editTextPu.getText().toString());

            Echantillon echantillonAjout = new Echantillon(code, label, stock);

            try {
                FileOutputStream fileOut = openFileOutput("EchantillonAjout.ser", MODE_PRIVATE);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(echantillonAjout);
                out.close();
                fileOut.close();
                Toast.makeText(this, "Echantillon ajouté dans le stock et sérialisé dans EchantillonAjout.ser", Toast.LENGTH_SHORT).show();
            } catch (IOException i) {
                i.printStackTrace();
            }
        } else if (view == btnQuitter) {
            Intent quitterAjoutEchantillon = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(quitterAjoutEchantillon);
            finish();
        }
    }

    // Classe Echantillon
    private static class Echantillon implements Serializable {
        private String code;
        private String label;
        private int stock;

        public Echantillon(String code, String label, int stock) {
            this.code = code;
            this.label = label;
            this.stock = stock;
        }

        public String getCode() {
            return code;
        }

        public String getLabel() {
            return label;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }
    }
}