package com.nabigeto.gavin.nabigetonabi.UserAccess;

import android.net.Uri;

/**
 * Created by Gavin on 4/23/2018.
 */

public class user_id {

    public String username;
    public String email;
    public String name;


    public user_id (){

    }

    public user_id(String username, String email, String name){

        this.username = username;
        this.email = email;
        this.name = name;

    }

    public String getUsername() {return username;}
    public String getEmail() {return email;}
    public String getName() {return  name;}

}
