package com.nabigeto.gavin.nabigetonabi.UserAccess;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.widget.ImageView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Gavin on 3/16/2018.
 */

public class Firebase_Authenitication_Fragment extends Fragment /* implements View.OnClickListener */{

public Firebase_Authenitication_Fragment(){}

    public ImageView d_signin;
    public ImageView d_signout;

    public static final String Gavin = "Gavin";
    private FirebaseAuth fAuth;

    public int RC_SIGN_IN = 1;
    public int RESULT_OK = -1;
    public int RESULT_E = 0;

    DatabaseReference mDatabase;

    public String email;
    public String name;
    public String userid;
    public Uri photoUrl;

    public String userid_queiry;

    public interface onResultCallback{
       void  onLoginResult(int Result_m);

    }


    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/**
        AuthUI.getInstance().signOut(getActivity().getApplicationContext());
**/
/**
        FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentuser != null){
            Log.v("Gavin", "User already logged in");
            resultCallback.onLoginResult(10);


        }

        else
        {

            **/
            Log.v("Gavin", "User not signed in, starting sign in");
            startsignin();






    }

    public void startsignin(){

        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build());
        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build(), RC_SIGN_IN);
        Log.v(Gavin, "Got here 2");
    }


    @Override
    public void onActivityResult(int requestCode, int resultsCode, final Intent data) {
        super.onActivityResult(requestCode, resultsCode, data);

        Log.v("Gavin", "Check place");
        Log.v("Gavin", Integer.toString(requestCode));
        Log.v("Gavin", Integer.toString(resultsCode));


        if (requestCode == 1) {

            IdpResponse response = IdpResponse.fromResultIntent(data);


            if (resultsCode == -1) {


                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                email = firebaseUser.getEmail();
                name = firebaseUser.getDisplayName();
                userid = firebaseUser.getUid();
                photoUrl = firebaseUser.getPhotoUrl();


                Log.v("Gavin", "Testy 1" + email + name + userid);

                final FirebaseDatabase mfirebasedatabase = FirebaseDatabase.getInstance();

                mfirebasedatabase.getReference().child("users").child(userid).addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        if (dataSnapshot.exists()) {
                            Log.v("Gavin", "Welcome back " + name);
                        } else {
                            Log.v("Gavin", "Writing new user" + email + userid + email);
                            user_id muser_id = new user_id(email, name, userid);
                            Log.v("Gavin", "Testy 2" + muser_id.username);
                            mDatabase = mfirebasedatabase.getReference();
                            mDatabase.child("users").child(userid).setValue(muser_id);


                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                        Log.v("Gavin", "Operation cancelled" + databaseError);
                    }
                });

                ((onResultCallback) getActivity()).onLoginResult(RESULT_OK);
            }
            else
                {
                    if (response == null) {

                        Log.v("Gavin", Integer.toString(requestCode));
                    }

                }





        }

    }

}