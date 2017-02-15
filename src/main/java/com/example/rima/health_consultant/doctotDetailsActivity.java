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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;


public class doctotDetailsActivity extends Activity {

    Context context;
    String UserName;
    ArrayList<String> Doctors = new ArrayList<>();
    ArrayList<DoctorTable> DoctorsTable = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctot_details);
        context = this;

        UserName = getIntent().getStringExtra("UserName");

        new AsyncDoctorDetails().execute();
    }

    protected class AsyncDoctorDetails extends AsyncTask<Void, Void, ArrayList<DoctorTable>> {
        ProgressDialog P;
        @Override
        protected ArrayList<DoctorTable> doInBackground(Void...params) {
            RestAPI api = new RestAPI();
            ArrayList<DoctorTable> List = null;
            try {

                // Call the User Authentication Method in API
                JSONObject jsonObj = api.GetDoctorDetails();

                //Parse the JSON Object to boolean
                JSONParser parser = new JSONParser();

                List = parser.parseDoctorDetails(jsonObj);


            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d("AsyncDoctorDetails", e.getMessage());

            }
            return List;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            P = ProgressDialog.show(context, "Please wait", "Loading doctor's list", true);
            //Toast.makeText(context, "Please Wait...",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(ArrayList<DoctorTable> result) {
            // TODO Auto-generated method stub

            int I;
            for (I = 0; I < result.size(); I++)
            {
                DoctorTable T = result.get(I);
                DoctorsTable.add(T);
                Doctors.add(T.DoctorName + "\nHospital : " + T.HospitalName + "\nSpecialist : " + T.Specialist + "\nOff Day : " + T.OffDay + "\nTime To Meet" + T.TimeToMeet);

            }

            ArrayAdapter<String> Adapter = new ArrayAdapter<String>(doctotDetailsActivity.this, R.layout.list_item, R.id.doc_name, Doctors);
            ListView V = (ListView)findViewById(R.id.doctors_list);
            V.setAdapter(Adapter);

            V.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent I = new Intent(doctotDetailsActivity.this, Patient_Account_Activity.class );
                    int DocID = (int)id;
                    DoctorTable T =  DoctorsTable.get(DocID);
                    I.putExtra("DoctorName", T.DoctorName);
                    I.putExtra("DoctorID", T.no);
                    I.putExtra("HospitalName", T.HospitalName);
                    I.putExtra("Specialist", T.Specialist);
                    I.putExtra("UserName", UserName);
                    startActivity(I);
                }
            });

            P.dismiss();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_doctot_details, menu);
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
