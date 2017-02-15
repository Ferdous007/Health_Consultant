package com.example.rima.health_consultant;

/**
 * Created by Rima on 1/1/2016.
 *
 */

public class UserDetailsTable {


    public String firstName, lastName, userName, password;

    public UserDetailsTable(String fn, String ln, String un, String pw) {
        firstName = fn;
        lastName = ln;
        userName = un;
        password = pw;
    }
}