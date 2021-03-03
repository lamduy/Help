package com.capstone2.funiturear;

import android.net.Uri;

public class User {
    private String name;
    private String email;
    private String gender;
    private String pass;
    private String Phone;

    public User() {
    }

    public User(String name, String gender,String phone, String email,String pass) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.Phone=phone;
        this.pass=pass;
    }
    //----------------------------------------
    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }


    //----------------------------------------
    public String getGender() {

        return gender;
    }

    public void setGender(String gender) {

        this.gender = gender;
    }

    //----------------------------------------

    public String getEmail() {

        return email;
    }
    public void setEmail(String email) {

        this.email = email;
    }

    //----------------------------------------
    public String getPass() {

        return pass;
    }
    public void setPass(String pass) {

        this.pass = pass;

    }

    //----------------------------------------

    public void setPhone(String phone){

        this.Phone=phone;
    }
    public String getPhone() {

        return Phone;
    }

}
