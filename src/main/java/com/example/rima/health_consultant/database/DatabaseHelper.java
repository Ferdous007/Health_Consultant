package com.example.rima.health_consultant.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.rima.health_consultant.model.Product;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SHARMI on 28/12/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
//public static final String DBNAME = "db_doc.db";
//public static final String DBNAME2 = "db_medic.db";
    public String DBNAME;
    public static final String DBLOCATION = "/data/data/com.example.rima.health_consultant/databases/";
    private Context mContext;
    public static SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context, String DB){
        super(context,DB,null, 1);
        DBNAME = DB;
        this.mContext= context;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase(){

        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if (mDatabase != null && mDatabase.isOpen()){
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase(){

        if (mDatabase!=null){
            mDatabase.close();
        }
    }

    public String GetProductSymptom(int ProductID)
    {
        openDatabase();
        Cursor C = mDatabase.rawQuery("SELECT * FROM Categories WHERE ID = " + String.valueOf(ProductID).toString(), null);
        C.moveToFirst();
        String Diseases = C.getString(2);
        closeDatabase();
        return Diseases;
    }

    public int GetMedicine(String Disease)
    {
        openDatabase();
        Cursor C = mDatabase.rawQuery("SELECT * FROM Medicine WHERE Diseases = '" + Disease + "'", null);
        C.moveToFirst();
        int Medicine_ID = C.getInt(0);
        closeDatabase();
        return Medicine_ID;
    }

    public String GetMedicine(int ID)
    {
        openDatabase();
        Cursor C = mDatabase.rawQuery("SELECT * FROM Medicine WHERE ID = " + String.valueOf(ID), null);
        C.moveToFirst();
        String Medicines = C.getString(2);
        closeDatabase();
        return Medicines;
    }

    public List<String> getDiseases()
    {
        openDatabase();
        Cursor c = mDatabase.rawQuery("SELECT * FROM Medicine", null);

        List<String> Diseases = new ArrayList<>();

        c.moveToFirst();
        while (!c.isAfterLast()){
            Diseases.add(c.getString(1));
            c.moveToNext();
        }

        c.close();
        closeDatabase();

        return Diseases;
    }

    public String GetInformation(String MedName)
    {
        openDatabase();
        Cursor C = mDatabase.rawQuery("SELECT * FROM Information WHERE Medicines = '" + MedName + "'", null);

        C.moveToFirst();

        String Info = C.getString(2);

        closeDatabase();
        return Info;
    }

    public boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(DBNAME);
            String outFileName = DBLOCATION + DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("Error", "DB copied");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Product>getListProduct(){
        Product product = null;
        List<Product>productList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM Categories", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            product = new Product(cursor.getInt(0),cursor.getString(1));
            productList.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return productList;
    }
}
