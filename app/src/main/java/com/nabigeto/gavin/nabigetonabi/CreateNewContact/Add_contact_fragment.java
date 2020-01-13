package com.nabigeto.gavin.nabigetonabi.CreateNewContact;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nabigeto.gavin.nabigetonabi.Contacts_Menu.contacts_menu_fragment;
import com.nabigeto.gavin.nabigetonabi.R;

public class Add_contact_fragment extends Fragment{

    public DatabaseReference mDatabase;
    public String user_id;
    public EditText name;
    public EditText eemail;

    public FirebaseUser firebaseUser;

    public Button submit_button;

    public Add_contact_fragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        user_id = firebaseUser.getUid();
        Log.v("Gavin", "Add_contact_fragment " + user_id);
    }


    @Override
    public View onCreateView(LayoutInflater inflator, ViewGroup group, Bundle onSavedInstanceState){
        super.onSaveInstanceState(onSavedInstanceState);

        View rootview =inflator.inflate(R.layout.fragment_add_contact, group, false );

        name = rootview.findViewById(R.id.contact_name);
        eemail = rootview.findViewById(R.id.contact_email);

        submit_button = rootview.findViewById(R.id.add_contact_submit_button);

        Log.v("Gavin", "Add_contact_fragment " + "launching");
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v("Gavin", "Add_contact_fragment " + " Button pressed");

                String mname = name.getText().toString();
                String memail = eemail.getText().toString();

                add_contact(mname,memail);

                Log.v("Gavin", "Add_contact_fragment " + memail);


            }
        });


        return rootview;
    }

    public void get_contacts(String user_id){

        mDatabase.getDatabase().getReference().child("contacts").child(user_id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                }
                else{

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void query_contact(final String user_id, final String email){

        Log.v("Gavin", "Add_contact_fragment " + "query_contact");

        FirebaseDatabase mfirebasedatabase = FirebaseDatabase.getInstance();

        mfirebasedatabase.getReference().child("contacts").child(user_id).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if (dataSnapshot.exists()) {
                    Log.v("Gavin", "Add_contact_fragment contact exists");
                            Toast.makeText(getContext(), "Hi - that contact already exists", Toast.LENGTH_LONG).show();

                } else {
                    Log.v("Gavin", "Add_contact_fragment add contact");
                    add_contact(user_id, email);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.v("Gavin", "Operation cancelled" + databaseError);
            }
        });








    }


    public void add_contact(String name, String email){

        contact_model Contact_model = new contact_model();

        Log.v("Gavin", "Add_contact_fragment " + user_id + email);

        Contact_model.email = email;
        Contact_model.name=name;

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("contacts");

        String key = mDatabase.child("contacts").child(user_id).push().getKey();

        Log.v("Gavin", "Add_contact_fragment " + "add_contact in public " + user_id + email);
        mDatabase.child("contacts").child(user_id).child(key).setValue(Contact_model).addOnSuccessListener(new OnSuccessListener<Void>(){
            @Override
            public void onSuccess(Void avoid){
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                contacts_menu_fragment Contacts_menu_fragment = new contacts_menu_fragment();
                fragmentTransaction.replace(R.id.activity_main, Contacts_menu_fragment);
                fragmentTransaction.commit();
            }

        });


    }

    public void remove_contact(){

    }
}
