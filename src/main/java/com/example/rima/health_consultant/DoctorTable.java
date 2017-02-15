package com.example.rima.health_consultant;

/**
 * Created by Rima on 1/8/2016.
 */
public class DoctorTable {


    public String DoctorName, Specialist, HospitalName, TimeToMeet, OffDay;
    public int no;
    public DoctorTable(int n, String dname, String sp, String hn, String time, String od) {
        no = n;
        DoctorName = dname;
        Specialist = sp;
        HospitalName = hn;
        TimeToMeet = time;
        OffDay=od;

    }
}