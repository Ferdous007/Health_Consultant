package com.example.rima.health_consultant;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class Create_Account_Activity extends Activity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__account_);

        context = this;

        findViewById(R.id.button_account_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              String firstname =  ((EditText) findViewById(R.id.editText_fn)).getText().toString();
              String lastname =  ((EditText) findViewById(R.id.editText_ln)).getText().toString();
              String password =  ((EditText) findViewById(R.id.editText_pw)).getText().toString();
              String username =  ((EditText) findViewById(R.id.editText_em)).getText().toString();

              UserDetailsTable userDetail = new UserDetailsTable(firstname,lastname, username,password);

              new AsyncCreateUser().execute(userDetail);

            }
        });
    }




    //own code

    protected class AsyncCreateUser extends
            AsyncTask<UserDetailsTable, Void, Void> {

        ProgressDialog P;

        @Override
        protected Void doInBackground(UserDetailsTable... params) {

            RestAPI api = new RestAPI();
            try {

                api.CreateNewAccount(params[0].firstName,
                        params[0].lastName, params[0].userName,
                        params[0].password);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d("AsyncCreateUser", e.getMessage());

            }
            return null;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            P = ProgressDialog.show(context, "Please wait", "Creating account", true);
        }

        @Override
        protected void onPostExecute(Void result) {
            P.dismiss();
            Intent i = new Intent(Create_Account_Activity.this, Login_Activity.class);
            startActivity(i);
        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create__account_, menu);
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
