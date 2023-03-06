package fr.louis.gsb.outils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySqLite extends SQLiteOpenHelper {

    // Propriétés
    private String creation="create table gellule ("
            + "codeproduit TEXT PRIMARY KEY,"
            + "libelleproduit NOT NULL,"
            + "description TEXT NOT NULL,"
            + "conseils TEXT NOT NULL,"
            + "dosage TEXT NOT NULL,"
            + "composition TEXT NOT NULL,"
            + "complement TEXT NOT NULL,"
            + "stock INTEGER NOT NULL,"
            + "stockmini INTEGER NOT NULL);";

    /**
     * Constructeur
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public MySqLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * si changement de BD
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(creation);
    }


    /**
     * Si changement de version
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
