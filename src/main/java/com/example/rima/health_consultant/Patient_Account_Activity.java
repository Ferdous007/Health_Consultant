package com.example.rima.health_consultant;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class Patient_Account_Activity extends Activity {

    EditText PatientName;
    EditText Email;
    EditText DoctorName;
    EditText Specialist;
    EditText HospitalName;
    EditText ReserveDate;

    int DoctorID;
    String UserName;
    String Doctor_Name;

    ProgressDialog P;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__account_);

        PatientName = (EditText) findViewById(R.id.editText_pn);
        Email = (EditText) findViewById(R.id.editText_pemail);
        DoctorName = (EditText) findViewById(R.id.editText_doctorname);
        Specialist = (EditText) findViewById(R.id.editText_specialist);
        HospitalName = (EditText) findViewById(R.id.editText_hname);
        ReserveDate =  (EditText) findViewById(R.id.editText_date);

        Intent I = getIntent();
        DoctorName.setText(I.getStringExtra("DoctorName"));
        HospitalName.setText(I.getStringExtra("HospitalName"));
        Specialist.setText(I.getStringExtra("Specialist"));
        DoctorID = I.getIntExtra("DoctorID", -1);
        UserName = I.getStringExtra("UserName");
        context = this;

        Doctor_Name = DoctorName.getText().toString();

        //own code
        findViewById(R.id.button_patient).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String patientname = PatientName.getText().toString();
                String emailid = Email.getText().toString();
                String doctorname = (DoctorName).getText().toString();
                String hospitalname = HospitalName.getText().toString();
                String date = ReserveDate.getText().toString();


                PatientDetailsTable patientDetail = new PatientDetailsTable(patientname,emailid, DoctorID,hospitalname,date, UserName);
                new AsyncCreateUser().execute(patientDetail);


            }
        });
    }

    //own code

    protected class AsyncCreateUser extends
            AsyncTask<PatientDetailsTable, Void, Void> {

        PatientDetailsTable T;

        @Override
        protected Void doInBackground(PatientDetailsTable... params) {

            RestAPI api = new RestAPI();
            try {

                api.CreateNewPatientAccount(params[0].patientname,
                        params[0].emailid, params[0].doctorno, params[0].hospitalname, params[0].tdate, params[0].un);
                T = params[0];

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d("AsyncCreateUser", e.getMessage());

            }
            return null;
        }



        @Override
        protected void onPostExecute(Void result) {
            P.dismiss();

            //Send a mail
            //Creating SendMail object
            String Body = T.patientname;
            Body = Body + "\n-----------------------------------\n";
            Body = Body + "Your time : " + T.tdate + "\n";
            Body = Body + "Place : " + T.hospitalname + "\n";
            Body = Body + "Doctor name : " + Doctor_Name + "\n";
            Body = Body + "Doctor id : " + DoctorID + "\n";
            Body = Body + "Your mail : " + T.emailid + "\n";
            Body = Body + "Do not reply to this mail.\n";

            SendMail sm = new SendMail(context,T.emailid, "Hospital Assistant App : Reservation", Body);

            //Executing sendmail to send email
            sm.execute();

            Intent i = new Intent(Patient_Account_Activity.this,Patient_details_Activity.class);
            startActivity(i);
        }

        @Override
        protected  void onPreExecute()
        {
            super.onPreExecute();
            P = ProgressDialog.show(context, "Please wait", "Doing reservation", true);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_patient__account_, menu);
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
