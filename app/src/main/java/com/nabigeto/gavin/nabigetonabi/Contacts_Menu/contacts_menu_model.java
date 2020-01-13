package com.nabigeto.gavin.nabigetonabi.Contacts_Menu;

public class contacts_menu_model {

    public String name;
    public String email;

    public contacts_menu_model(){

    };

    public contacts_menu_model(String name, String email){

        this.name = name;
        this.email = email;

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
}
