package com.example.rima.health_consultant;

/**
 * Created by Rima on 1/8/2016.
 */
public class PatientDetailsTable {

        public String patientname,emailid,hospitalname,tdate, un;
        public int doctorno;
        public PatientDetailsTable(String pn, String em, int dn, String hn,String d, String uname)
        {
            patientname=pn;
            emailid=em;
            doctorno = dn;
            hospitalname=hn;
            tdate=d;
            un = uname;
        }




}

