package com.example.rima.health_consultant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rima.health_consultant.adapter.ListProductAdapter;
import com.example.rima.health_consultant.database.DatabaseHelper;
import com.example.rima.health_consultant.model.Product;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by SHARMI on 28/12/2015.
 */
public class MainActivity extends Activity {
    private ListView lvProduct;
    private ListProductAdapter adapter;
    private List<Product> mProductList;
    static DatabaseHelper mDBHelper;
    static DatabaseHelper DB_Helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        lvProduct =(ListView)findViewById(R.id.listview_product);
        mDBHelper = new DatabaseHelper(this, "db_doc.db");

        DB_Helper = new DatabaseHelper(this, "db_medic.db");
        File database = getApplicationContext().getDatabasePath(DB_Helper.DBNAME);
        DB_Helper.getReadableDatabase();
        DB_Helper.copyDatabase(this);

        //Check exists database
        database = getApplicationContext().getDatabasePath(mDBHelper.DBNAME);
        mDBHelper.getReadableDatabase();
        mDBHelper.copyDatabase(this);

        /*
        if(database.exists() == false) {
            mDBHelper.getReadableDatabase();
            //Copy db
            if(copyDatabase(this)) {
                Toast.makeText(this, "Copy database succes", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Copy data error", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        */

        //Get product list in db when db exists
        mProductList = mDBHelper.getListProduct();
        //Init adapter
        adapter = new ListProductAdapter(this, mProductList);
        //Set adapter for listview
        lvProduct.setAdapter(adapter);
        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText (MainActivity.this, ((TextView) view.findViewById(R.id.tv_product_physicalproblem)).getText().toString(), Toast.LENGTH_SHORT);
                Intent I = new Intent(MainActivity.this, SymptomActivity.class);
                I.putExtra("Selection", id);
                startActivity(I);
            }
        });

    }


    }

