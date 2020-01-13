package com.nabigeto.gavin.nabigetonabi.UserAccess;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nabigeto.gavin.nabigetonabi.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppWelcome_Fragment extends Fragment implements TextToSpeech.OnInitListener {

    public TextView viewname;
    public TextView viewuseremail;
    public ImageView viewphotourl;

    private int MY_DATA_CHECK_CODE = 100;

    private String name;
    private String email;
    private Uri photoUrl;
    private String userid;

    private DatabaseReference databaseReference;

    private FirebaseAuth user_Auth;

    private String check1;

    private String text_to_read;

    private String speechtext_1;

    private DatabaseReference mDatabase;

    public FirebaseDatabase gDatabase;

    private TextToSpeech speecht;

    public AppWelcome_Fragment() {
        // Required empty public constructor
    }

    onTTSDoneCallback TTSDoneCallback;

    public interface onTTSDoneCallback {
        public void Welcome_Done(Boolean finishedTTS);


    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            name = currentUser.getDisplayName();
            email = currentUser.getEmail();
            photoUrl = currentUser.getPhotoUrl();
            userid = currentUser.getUid();


            check1 = photoUrl.toString();

            Log.v("Gavin", "Check " + name);
            Log.v("Gavin", "Check " + email);
            Log.v("Gavin", "Check " + check1);



        } else {

            Toast.makeText(getContext(), "Oh, there seems to be an error", Toast.LENGTH_LONG);
        }

        database_update(mDatabase,userid, name,email,photoUrl);

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            TTSDoneCallback = (onTTSDoneCallback) context;
        }
        catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.app_welcome_fragment, container, false);
        viewname = (TextView) rootView.findViewById(R.id.app_user_name);
        viewuseremail = (TextView) rootView.findViewById(R.id.app_user_email);
        viewphotourl = (ImageView) rootView.findViewById(R.id.app_user_photo);


        return rootView;
    }
/**
    private void writenewuser(String username, String userid, String email) {
        user_id user_idA = new user_id(username, email);

        mDatabase.child("users").child(userid).setValue(user_idA);

    }
**/


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v("Gavin", "Starting activity");
        welcome_screen_details_loader();
        welcom_screen_details_audio();

    }


    private void welcome_screen_details_loader() {

        Log.v("Gavin", "Loading welcome screen loader 1");
        viewname.setText(name);
        viewuseremail.setText(email);
        Picasso.with(getContext()).load(photoUrl).into(viewphotourl);
        Log.v("Gavin", "Loading welcome screen loader 2");
    }

    private void welcom_screen_details_audio() {

        Intent intent = new Intent();

        intent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(intent, MY_DATA_CHECK_CODE);

        Log.v("Gavin", "Loading welcom screen details audio");

    }

    @Override
    public void onActivityResult(int requestCode, int resultsCode, Intent data) {
        super.onActivityResult(requestCode, resultsCode, data);
            Log.v("Gavin", "Onactivityresult");
        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultsCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {

                speecht = new TextToSpeech(getContext(), this);
/**
                speecht = new TextToSpeech(getContext(), this);

                TTSListner();

                speechtext_1 = "one";

                Bundle params = new Bundle();
                params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, speechtext_1);

                text_to_read = getResources().getString(R.string.Welcome_message);

                speecht.speak(text_to_read + "   " + name, TextToSpeech.QUEUE_FLUSH, params, speechtext_1);

**/
                };


                        } else {
                            Log.e("Gavin", "Error loading - please try again");
                        }
                    }



    @Override
    public void onInit(int status){
    if(status==TextToSpeech.SUCCESS){
    Log.v("Gavin", "oninit success");


        TTSListner();

        speechtext_1 = "one";

        Bundle params = new Bundle();
        params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, speechtext_1);

        text_to_read = getResources().getString(R.string.Welcome_message);

        speecht.speak(text_to_read + "   " + name, TextToSpeech.QUEUE_FLUSH, params, speechtext_1);

    }
            }

            private void TTSListner(){
            int listner_result = speecht.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                @Override
                public void onStart(String utteranceId) {
                Log.v("Gavin", "TTS Started");
                }

                @Override
                public void onDone(String utteranceId) {
                Log.v("Gavin", "TTS Done");

                TTSDoneCallback.Welcome_Done(Boolean.TRUE);

                }

                @Override
                public void onError(String utteranceId) {
                    Log.v("Gavin", "TTS Error");
                }
            });

            }

            private void database_update(DatabaseReference databaseReference, String userid1, String name1, String email1, Uri photoUrl1){


                Log.v("Gavin", "updating database");





                DatabaseReference mDatabase;

                mDatabase = FirebaseDatabase.getInstance().getReference();

               mDatabase.child("users").child("adID");

                String duser_id = mDatabase.push().getKey();

                Log.v("Gavin", duser_id);

                user_id muser_id = new user_id(userid,name, email);

                mDatabase.child(duser_id).setValue(muser_id, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference){}
                });

                Log.v("Gavin", "updating database 1");
            }


}




