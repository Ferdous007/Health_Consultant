package com.example.rima.health_consultant;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {

    public JSONParser()
    {
        super();
    }


    public boolean parseUserAuth(JSONObject object)
    {     boolean userAtuh=false;
        try {
            userAtuh= object.getBoolean("Value");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseUserAuth", e.getMessage());
        }

        return userAtuh;
    }

    public UserDetailsTable parseUserDetails(JSONObject object)
    {
        UserDetailsTable userDetail;
        userDetail = new UserDetailsTable("","","","");

        try {
            JSONObject jsonObj=object.getJSONArray("Value").getJSONObject(0);

            userDetail.firstName=jsonObj.getString("firstName");
            userDetail.lastName=jsonObj.getString("lastName");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseUserDetails", e.getMessage());
        }

        return userDetail;

    }

    public ArrayList<DoctorTable> parseDoctorDetails(JSONObject object)
    {

        ArrayList<DoctorTable> DoctorList = new ArrayList<DoctorTable>();
        try{

            JSONArray jsonArray=object.getJSONArray("Value");
            JSONObject jsonObj=null;
            for(int i=0;i<jsonArray.length();i++)
            {
                jsonObj=jsonArray.getJSONObject(i);
                DoctorList.add(new DoctorTable(jsonObj.getInt("no"), jsonObj.getString("DoctorName"),jsonObj.getString("Specialist"),jsonObj.getString("HospitalName"),jsonObj.getString("TimeToMeet"),jsonObj.getString("OffDay")));
            }

        }
        catch(JSONException e)
        {
            Log.d("JSONParser => parseDepartment", e.getMessage());
        }
        return DoctorList;
    }
}