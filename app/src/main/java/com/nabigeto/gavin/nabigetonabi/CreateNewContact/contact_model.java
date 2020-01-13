package com.nabigeto.gavin.nabigetonabi.CreateNewContact;

public class contact_model {

    public String name;
    public String email;

    public contact_model(){

    };

    public contact_model(String name, String email){

        this.name=name;
        this.email=email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public contact_model(String email){

        this.email = getEmail();

    }




}
