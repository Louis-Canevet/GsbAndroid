package fr.louis.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fr.louis.gsb.R;

public class MajEchantillon extends AppCompatActivity implements View.OnClickListener{

    // Propriétés

    private TextView txtMajEchantillon;
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

        txtMajEchantillon = (TextView) findViewById(R.id.txtMajEchantillon);
        editTextCodeMaj = (EditText) findViewById(R.id.editTextCodeMaj);
        editTextQteMaj = (EditText) findViewById(R.id.editTextQteMaj);
        btnAjouterMaj = (Button) findViewById(R.id.btnAjouterMaj);
        btnQuitterMaj = (Button) findViewById(R.id.btnQuitterMaj);
        btnSupprimerMaj = (Button) findViewById(R.id.btnSupprimerMaj);
        txtElementAjouter = (TextView) findViewById(R.id.txtElementAjouter);

        btnAjouterMaj.setOnClickListener((View.OnClickListener) this);

        this.btnQuitterMaj = (Button) findViewById(R.id.btnQuitterMaj);
        btnQuitterMaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent quitterMaj = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(quitterMaj);
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (btnAjouterMaj == view) {
            // actions
            donnees = donnees + " / ";
            txtElementAjouter.setText(donnees);
        }
    }
}