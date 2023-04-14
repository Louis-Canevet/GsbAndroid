package fr.louis.gsb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fr.louis.gsb.Echantillon;
import fr.louis.gsb.outils.BdAdapter;


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

        testBd1();

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

    public void testBd1() {
        //Création d'une instance de la classe echantBDD
        BdAdapter echantBdd = new BdAdapter(this);
        //Création d'un Echantillon
        //Echantillon unEchant = new Echantillon("code1", "lib1", 3);
        //On ouvre la base de données pour écrire dedans
        echantBdd.open();
        //On insère l'echantillon que l'on vient de créer
        //echantBdd.insererEchantillon(unEchant);
        //System.out.println("insertion echantillon");
        //Pour vérifier que l'on a bien créé un echantillon dans la BDD
        //on extrait l’echantillon de la BDD grâce au libelle de l'echantillon que l'on a créé précédemment
        Echantillon unEchantFromBdd = echantBdd.getEchantillonWithLib("lib1");
        //Si un unArticle est retourné (donc si le unEchant à bien été ajouté à la BDD)
        if (unEchantFromBdd != null) {
            //On affiche les infos de l’echantillon dans un Toast
            Toast.makeText(this, unEchantFromBdd.getLabel(), Toast.LENGTH_LONG).show();
            //System.out.println("echantillon trouve : " + unEchantFromBdd.getLabel());
            //On modifie le titre de l’echantillon
            unEchantFromBdd.setLabel("lib2");
            //Puis on met à jour la BDD
            echantBdd.updateEchantillon(unEchantFromBdd.getCode(), unEchantFromBdd.getQuantiteStock());
        } else {
            Toast.makeText(this, "echantillon non trouvé", Toast.LENGTH_LONG).show();
            //System.out.println("echantillon non trouvé");
        }
        //On extrait l’Article de la BDD grâce à son nouveau lib
        unEchantFromBdd = echantBdd.getEchantillonWithLib("Lib2");
        //S'il existe un Article possédant cette désignation dans la BDD
        if (unEchantFromBdd != null) {
            //On affiche les nouvelles info de l’echantillon pour vérifié que le lib de l’echantillon a bien été maj
            Toast.makeText(this, unEchantFromBdd.getLabel(), Toast.LENGTH_LONG).show();
            //on supprime le unEchant de la BDD grâce à son ID
            echantBdd.removeEchantillonWithCode(unEchantFromBdd.getCode());
        }
        //On essait d'extraire de nouveau l’echantillon de la BDD toujours grâce à son nouveau libelle
        unEchantFromBdd = echantBdd.getEchantillonWithLib("lib2");
        //Si aucun unEchant n'est retourné
        if (unEchantFromBdd == null) {
            //On affiche un message indiquant que l’echantillon n'existe pas dans la BDD
            Toast.makeText(this, "Cet echantillon n'existe pas dans la BDD", Toast.LENGTH_LONG).show();
        } else { //Si l'Aechantillon existe (mais normalement il ne devrait pas)
            //on affiche un message indiquant que l’echantillon existe dans la BDD
            Toast.makeText(this, "Cet echantillon existe dans la BDD", Toast.LENGTH_LONG).show();
        }
        echantBdd.close();
    }
/*
        public void testBd2() {
            //Création d'une instance de la classe echantBDD
            BdAdapter echantBdd = new BdAdapter(this);
            //On ouvre la base de données pour écrire dedans
            echantBdd.open();
            //On insère DES ECHANTILLONS DANS LA BD
            echantBdd.insererEchantillon(new Echantillon("code1", "lib1", 3));
            echantBdd.insererEchantillon(new Echantillon("code2", "lib2", 5));
            echantBdd.insererEchantillon(new Echantillon("code3", "lib3", 7));
            echantBdd.insererEchantillon(new Echantillon("code4", "lib4", 6));
            Cursor unCurseur = echantBdd.getData();
            System.out.println("il y a " + String.valueOf(unCurseur.getCount()) + " echantillons dans la BD");
            echantBdd.close();
        }*/

}