package fr.louis.gsb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fr.louis.gsb.Echantillon;
import fr.louis.gsb.outils.BdAdapter;

public class AjoutEchantillon extends AppCompatActivity implements View.OnClickListener {

    // propriété
    private EditText editTextCode;
    private EditText editTextLibelle;
    private EditText editTextQuantite;
    private Button btnAjouter;
    private Button btnQuitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_echantillon);

        editTextCode = findViewById(R.id.editTextCode);
        editTextLibelle = findViewById(R.id.editTextLibelle);
        editTextQuantite = findViewById(R.id.editTextQuantite);
        btnAjouter = findViewById(R.id.btnAjouter);
        btnQuitter = findViewById(R.id.btnQuitter);

        btnAjouter.setOnClickListener(this);
        btnQuitter.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == btnAjouter) {
            String code = editTextCode.getText().toString();
            String label = editTextLibelle.getText().toString();
            String quantiteStock = editTextQuantite.getText().toString();

            if (code.isEmpty() || label.isEmpty() || quantiteStock.isEmpty()) {
                // Afficher un message d'erreur si un ou plusieurs champs sont vides
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            } else {
                // Convertir la quantité en entier

                // Créer un nouvel objet Echantillon et l'ajouter à la base de données
                Echantillon echantillonAjout = new Echantillon(code, label, quantiteStock);

                BdAdapter bdAdapter = new BdAdapter(this);
                bdAdapter.open();
                bdAdapter.insererEchantillon(echantillonAjout);
                bdAdapter.close();

                // Afficher un message de confirmation à l'utilisateur
                Toast.makeText(this, "Echantillon ajouté dans le stock", Toast.LENGTH_SHORT).show();

                // Effacer les champs de texte
                editTextCode.setText("");
                editTextLibelle.setText("");
                editTextQuantite.setText("");
            }
        } else if (view == btnQuitter) {
            // Terminer l'activité et retourner à l'activité principale
            Intent quitterAjoutEchantillon = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(quitterAjoutEchantillon);
            finish();
        }
    }


}
