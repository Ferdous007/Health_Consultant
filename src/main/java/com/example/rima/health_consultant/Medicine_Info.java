package com.example.rima.health_consultant;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rima.health_consultant.database.DatabaseHelper;

import java.util.ArrayList;

public class Medicine_Info extends Activity {

    DatabaseHelper DB_Helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine__info);
        DB_Helper = MainActivity.DB_Helper;

        Intent I = getIntent();
        long ID =  I.getLongExtra("Selection", -1);

        if (ID > -1)
        {
            String Medicines = DB_Helper.GetMedicine((int)(ID));
            ArrayList<String> List = new ArrayList<String>();
            String[] S = Medicines.split(",");
            int C;
            for (C = 0; C < S.length; C++)
            {
                List.add(S[C]);
            }

            ListView L = (ListView)findViewById(R.id.medicine_list);
            ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this,R.layout.item_listview,R.id.tv_product_physicalproblem, List);

            L.setAdapter(Adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_medicine__info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
