package fr.louis.gsb;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fr.louis.gsb.outils.BdAdapter;
import fr.louis.gsb.outils.CreateBdEchantillon;

public class MajEchantillon extends AppCompatActivity implements View.OnClickListener {

    // Propriétés
    private EditText editTextCodeMaj;
    private EditText editTextQteMaj;
    private Button btnQuitterMaj;
    private Button btnAjouterMaj;
    private Button btnSupprimerMaj;
    private String donnees;
    private TextView txtElementAjouter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maj_echantillon);

        editTextCodeMaj = findViewById(R.id.editTextCodeMaj);
        editTextQteMaj = findViewById(R.id.editTextQteMaj);
        btnAjouterMaj = findViewById(R.id.btnAjouterMaj);
        btnQuitterMaj = findViewById(R.id.btnQuitterMaj);
        btnSupprimerMaj = findViewById(R.id.btnSupprimerMaj);
        txtElementAjouter = findViewById(R.id.txtElementAjouter);

        btnAjouterMaj.setOnClickListener(this);
        btnSupprimerMaj.setOnClickListener(this);

        btnQuitterMaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent quitterMaj = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(quitterMaj);
                finish();
            }
        });
    }

    public void updateEchantillon(String code, int quantiteStock) {
        // Ouvrir la base de données
        SQLiteDatabase db = new CreateBdEchantillon(this, "echantillons.db", null, CreateBdEchantillon.VERSION_BDD).getWritableDatabase();

        // Créer un objet ContentValues avec les nouvelles valeurs
        ContentValues values = new ContentValues();
        values.put(CreateBdEchantillon.COL_STOCK, quantiteStock);

        // Mettre à jour l'échantillon dans la base de données
        int numRowsAffected = db.update(CreateBdEchantillon.TABLE_ECHANT, values, CreateBdEchantillon.COL_CODE + " = ?", new String[]{code});

        // Vérifier si la mise à jour a réussi
        if (numRowsAffected > 0) {
            Toast.makeText(this, "L'échantillon a été modifié", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "La modification a échoué", Toast.LENGTH_SHORT).show();
        }

        // Fermer la base de données
        db.close();
    }

    @Override
    public void onClick(View view) {
        if (view == btnAjouterMaj) {
            String code = editTextCodeMaj.getText().toString();
            String quantiteStock = editTextQteMaj.getText().toString();

            //Echantillon echantillonAjout = new Echantillon(code, quantiteStock);
            if (code.isEmpty() || quantiteStock.isEmpty()) {
                // Afficher un message d'erreur si un ou plusieurs champs sont vides
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            }
            else {
                BdAdapter bdAdapter = new BdAdapter(this);
                bdAdapter.open();
                bdAdapter.updateEchantillon(code, quantiteStock);
                //System.out.println(bdAdapter.updateEchantillon(code ,quantiteStock));
                bdAdapter.close();
            }

            // Ajouter un enregist
            txtElementAjouter.setText("Element ajouté :D ");

        } else if (view == btnSupprimerMaj) {
            String code = editTextCodeMaj.getText().toString();
            String quantiteStock = editTextQteMaj.getText().toString();
            //Echantillon echantillonAjout = new Echantillon(code, quantiteStock);
            if (code.isEmpty() || quantiteStock.isEmpty()) {
                // Afficher un message d'erreur si un ou plusieurs champs sont vides
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            }
            else {
                BdAdapter bdAdapter = new BdAdapter(this);
                bdAdapter.open();
                bdAdapter.removeEchantillonWithCode(code);
                bdAdapter.close();
            }
            // Supprimer l'élément de la liste
            txtElementAjouter.setText("Element supprimé : " + code);

        }
    }
}

