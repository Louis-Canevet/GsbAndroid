package fr.louis.gsb.outils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fr.louis.gsb.Echantillon;

public class BdAdapter {
    private static final String NOM_BDD = "gsb.db";
    private static final String TABLE_ECHANT = "echantillons";
    static final String COL_ID = "_id";
    static final int NUM_COL_ID = 0;
    public static final String COL_CODE = "CODE";
    static final int NUM_COL_CODE = 1;
    public static final String COL_LABEL = "LABEL";
    static final int NUM_COL_LABEL = 2;
    public static final String COL_STOCK = "STOCK";
    static final int NUM_COL_STOCK = 3;

    private CreateBdEchantillon bdArticles;
    private Context context;
    private SQLiteDatabase db;

    public BdAdapter(Context context) {
        this.context = context;
        bdArticles = new CreateBdEchantillon(context, NOM_BDD, null, CreateBdEchantillon.VERSION_BDD);
    }

    public BdAdapter open() {
        db = bdArticles.getWritableDatabase();
        return this;
    }

    public BdAdapter close() {
        db.close();
        return this;
    }

    public long insererEchantillon(Echantillon unEchant) {
        ContentValues values = new ContentValues();
        values.put(COL_CODE, unEchant.getCode());
        values.put(COL_LABEL, unEchant.getLabel());
        values.put(COL_STOCK, unEchant.getQuantiteStock()); // Correction : changer getQuantiteStock() en getStock()
        if (unEchant.getQuantiteStock() == null) {
            return -1; // ou une autre valeur de retour pour indiquer une erreur
        }
        return db.insert(TABLE_ECHANT, null, values);
    }

    private Echantillon cursorToEchant(Cursor c) {
        Echantillon unEchant = null;
        if (c.getCount() != 0) {
            c.moveToFirst();
            //unEchant = new Echantillon(code, quantiteStock);
            unEchant.setCode(c.getString(NUM_COL_CODE));
            unEchant.setQuantiteStock(c.getString(NUM_COL_STOCK)); // Correction : changer getQuantiteStock() en getStock()
        }
        c.close();
        return unEchant;
    }

    public Echantillon getEchantillonWithLib(String unCode) {
        Cursor c = db.query(TABLE_ECHANT, new String[]{COL_ID, COL_CODE, COL_LABEL, COL_STOCK}, COL_CODE + " LIKE \"" + unCode + "\"", null, null, null, null);
        return cursorToEchant(c);
    }

    public int updateEchantillon(String unCode , String qteStock) {
        ContentValues values = new ContentValues();
        values.put(COL_CODE, unCode);
        //System.out.println(unCode);
        values.put(COL_STOCK, qteStock); // Correction : changer getQuantiteStock() en getStock()
        //System.out.println(qteStock);
        return db.update(TABLE_ECHANT, values, COL_CODE + " = \"" + unCode + "\"", null);
    }

    public int removeEchantillonWithCode(String unCode) {
        return db.delete(TABLE_ECHANT, COL_CODE + " = \"" + unCode + "\"", null);
    }

    public Cursor getData() {
        return db.rawQuery("SELECT * FROM " + TABLE_ECHANT, null);
    }
}
