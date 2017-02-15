package com.example.rima.health_consultant;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class Welcome_activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome_activity);


        //Creating SendMail object
       // SendMail sm = new SendMail(this, "rifathaqueamit@gmail.com", "no subject", "no message");

        //Executing sendmail to send email
        //sm.execute();


        //own code..........



        findViewById(R.id.button_welcome).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

        //Intent I = new Intent(Welcome_activity.this, MapsActivity.class);
        // startActivity(I);
        Intent intent = new Intent(Welcome_activity.this, Select_activity.class);
        startActivity(intent);
         }
                   });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome_activity, menu);
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
