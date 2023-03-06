package fr.louis.gsb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class AjoutEchantillon extends AppCompatActivity implements View.OnClickListener{

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

        txtSaisieEchantillon = (TextView) findViewById(R.id.txtSaisieEchantillon);
        editTextRef = (EditText) findViewById(R.id.editTextRef);
        editTextDesign = (EditText) findViewById(R.id.editTextDesign);
        editTextPu = (EditText) findViewById(R.id.editTextPu);
        btnAjouter = (Button) findViewById(R.id.btnAjouter);
        btnQuitter = (Button) findViewById(R.id.btnQuitter);
        txtAffichage = (TextView) findViewById(R.id.txtAffichage);

       btnAjouter.setOnClickListener((View.OnClickListener) this);
       donnees="";

       btnQuitter.setOnClickListener((View.OnClickListener) this);

    }

/*
        //this.btnQuitter = (Button) findViewById(R.id.btnQuitter);
        btnQuitter.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent quitterAjoutEchantillon = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(quitterAjoutEchantillon);
            finish();
        }
    });*/

    @Override
    public void onClick(View view) {

        this.btnAjouter = (Button) findViewById(R.id.btnAjouter);
        btnAjouter.setOnClickListener((View.OnClickListener) this);
        class btnAjouter implements java.io.Serializable
        {
            private String code;
            private String label;
            private int stock;

            public btnAjouter(String code, String label, int stock) {
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

            public void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter code echantillon: ");
                String code = scanner.nextLine();
                System.out.print("Enter label echantillon: ");
                String label = scanner.nextLine();
                System.out.print("Enter stock: echantillon");
                int stock = scanner.nextInt();
                scanner.close();

                btnAjouter echantillonAjout = new btnAjouter(code, label, stock);

                try {
                    FileOutputStream fileOut = new FileOutputStream("EchantillonAjout.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(btnAjouter);
                    out.close();
                    fileOut.close();
                    System.out.println("Echantillon ajouter dans stock et sérialiser EchantillonAjout.ser");
                } catch (IOException i) {
                    i.printStackTrace();
                }
            }
        }

        this.btnQuitter = (Button) findViewById(R.id.btnQuitter);
        btnQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent quitterAjoutEchantillon = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(quitterAjoutEchantillon);
                finish();
            }
        });
    }

}