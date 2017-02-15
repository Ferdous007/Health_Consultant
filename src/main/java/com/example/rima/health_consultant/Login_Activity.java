package com.example.rima.health_consultant;

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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;


public class Login_Activity extends Activity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        context=this;

        findViewById(R.id.button_login_ok).setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           String Username = ((EditText)findViewById(R.id.editText_log_un)).getText().toString();
           String Password= ((EditText)findViewById(R.id.editText_log_pass)).getText().toString();
           new  AsyncLogin().execute(Username, Password);
       }
   });


    }



    //own code

    protected class AsyncLogin extends AsyncTask<String, JSONObject, Boolean> {

        String userName=null;
        ProgressDialog P;

        @Override
        protected Boolean doInBackground(String... params) {

            RestAPI api = new RestAPI();
            boolean userAuth = false;
            try {

                // Call the User Authentication Method in API
                JSONObject jsonObj = api.UserAuthentication(params[0], params[1]);

                //Parse the JSON Object to boolean
                JSONParser parser = new JSONParser();

                userAuth = parser.parseUserAuth(jsonObj);
                userName=params[0];
            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d("AsyncLogin", e.getMessage());

            }
            return userAuth;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            P = ProgressDialog.show(context, "Please wait", "Logging into account", true);
           // Toast.makeText(context, "Please Wait...",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            // TODO Auto-generated method stub

            //Check user validity
            if (result) {
                P.dismiss();

                Intent intent=new Intent(Login_Activity.this,doctotDetailsActivity.class);
                intent.putExtra("UserName", userName);
                startActivity(intent);
            }
            else
            {
                P.dismiss();

                Toast.makeText(context, "Not valid username/password ", Toast.LENGTH_SHORT).show();
            }

        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_, menu);
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
