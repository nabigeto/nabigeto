package com.nabigeto.gavin.nabigetonabi;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.nabigeto.gavin.nabigetonabi.CreateNewReminder.Set_New_Reminder_Fragment;
import com.nabigeto.gavin.nabigetonabi.EditReminder.Edit_Reminder_Fragment;
import com.nabigeto.gavin.nabigetonabi.Reminder_Menu.Reminder_Menu_Fragment;
import com.nabigeto.gavin.nabigetonabi.Contacts_Menu.contacts_menu_fragment;
import com.nabigeto.gavin.nabigetonabi.CreateNewContact.Add_contact_fragment;
import com.nabigeto.gavin.nabigetonabi.UserAccess.AppWelcome_Fragment;
import com.nabigeto.gavin.nabigetonabi.UserAccess.Error_Fragment;
import com.nabigeto.gavin.nabigetonabi.UserAccess.Firebase_Authenitication_Fragment;
import com.nabigeto.gavin.nabigetonabi.archive.Menu_Fragment;
import com.nabigeto.gavin.nabigetonabi.archive.background_service;

/**
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
**/


/**
 * Created by Gavin on 3/13/2018.
 */

public class MainActivity extends AppCompatActivity implements Reminder_Menu_Fragment.OnTouchlistner, Firebase_Authenitication_Fragment.onResultCallback, AppWelcome_Fragment.onTTSDoneCallback {


    public static final String KEY_FILE = "KEY";
    public static final String Gavin = "Gavin";

    DatabaseReference mDatabase;

    public String email;
    public String name;
    public String userid;
    public Uri photoUrl;



    View view_id;

    public static final String SERVICE_LIVE = background_service.class.getName() + "LIVE";
    public static final String SERVICE_ACTION = background_service.class.getName() + "ACTION";

    public static final int RC_SIGN_IN = 1;
    public static final int RESULT_OK = -1;
    public static final int RESULT_E = 0;

    private static final int JOBID = 1;

    private boolean edit_t = false;

    Reminder_Menu_Fragment.OnTouchlistner onTouchlistner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("Gavin", "MainActivity" + " starting");

        setContentView(R.layout.main_activity);

        view_id = findViewById(R.id.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Log.v("Gavin", "Main Activity" + "work manager starting");
/**
        PeriodicWorkRequest alarms_schedule = new PeriodicWorkRequest.Builder(onstart_worker.class, 15 , TimeUnit.MINUTES).build();

        WorkManager.getInstance().enqueueUniquePeriodicWork(userid, ExistingPeriodicWorkPolicy.REPLACE, alarms_schedule);
**/
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            firebaseAuth.getUid();


            /**     AppWelcome_Fragment fragment_a = new AppWelcome_Fragment();
             **/
            Reminder_Menu_Fragment reminder_menu_fragment = new Reminder_Menu_Fragment();
            fragmentTransaction.add(R.id.activity_main, reminder_menu_fragment);
            fragmentTransaction.commit();
            Log.v(Gavin, "Main Activity Got here 1");
            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

            email = firebaseUser.getEmail();
            name = firebaseUser.getDisplayName();
            userid = firebaseUser.getUid();
            photoUrl = firebaseUser.getPhotoUrl();

            Log.v("Gavin", email + name + userid);


        } else {

            Firebase_Authenitication_Fragment firebase_authenitication_fragment = new Firebase_Authenitication_Fragment();
            fragmentTransaction.add(R.id.activity_main, firebase_authenitication_fragment);
            fragmentTransaction.commit();
            Log.v(Gavin, "Main Activity Got here 1b");


        }
        ;



}

@Override
public void onAttachFragment(Fragment fragment){

        if(fragment instanceof Reminder_Menu_Fragment){
            Reminder_Menu_Fragment reminder_menu_fragment = (Reminder_Menu_Fragment) fragment;
            reminder_menu_fragment.setOnTouchListner(this);

        }
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.app_menu, menu);
        return true;
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_menu_1:
                logout();

            return true;

            case R.id.app_menu_2:
                new_reminder();

            return true;

            case R.id.app_menu_3:
                reminder_menu();

                return true;

            case R.id.app_menu_4:
                add_contact();

                return true;

            case R.id.app_menu_5:
                contacts_menu();

            default:
                return super.onOptionsItemSelected(item);
        }

    }





    @Override
    public void onLoginResult(int Result){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Log.v("Gavin", "Check 1" + Integer.toString(Result));



        switch (Result) {

            case RESULT_OK:
                Log.v("Gavin", "Check" + Integer.toString(Result));
               /**
                * AppWelcome_Fragment fragment_a = new AppWelcome_Fragment();
               **/
  /**              FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();



                email = firebaseUser.getEmail();
                name = firebaseUser.getDisplayName();
                userid = firebaseUser.getUid();
                photoUrl = firebaseUser.getPhotoUrl();

                Log.v("Gavin", email + name + userid);

                FirebaseDatabase mfirebasedatabase = FirebaseDatabase.getInstance();

                mDatabase = mfirebasedatabase.getReference();


                user_id muser_id = new user_id(email, name, userid);

                mDatabase.child("users").child(userid).setValue(muser_id);
**/

                Reminder_Menu_Fragment remind_menu_fragment = new Reminder_Menu_Fragment();
                fragmentTransaction.replace(R.id.activity_main, remind_menu_fragment);
                fragmentTransaction.commit();
                break;

            case RESULT_E:
                Error_Fragment fragment_b = new Error_Fragment();
                fragmentTransaction.replace(R.id.activity_main, fragment_b);
                fragmentTransaction.commit();
                Log.v("Gavin", Integer.toString(Result));
                break;
        }

        /**
        if (Result == RESULT_OK){
            AppWelcome_Fragment fragment_a = new AppWelcome_Fragment();
            fragmentTransaction.replace(R.id.activity_main, fragment_a);
            fragmentTransaction.commit();
        }

        if (Result == RESULT_E){
            Error_Fragment fragment_a = new Error_Fragment();
            fragmentTransaction.replace(R.id.activity_main, fragment_a);
            fragmentTransaction.commit();

        }

        else {
            Firebase_Authenitication_Fragment fragment_a = new Firebase_Authenitication_Fragment();
            fragmentTransaction.replace(R.id.activity_main, fragment_a);
            fragmentTransaction.commit();
        }
    }
**/


}

    @Override
    public void onStop(){
    super.onStop();
    LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
    }

    public BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(SERVICE_LIVE)){
                Log.v("Gavin", "Main Activity" + "service live");


            }
            else {
                    Log.v("Gavin", "background_service" + " starting service none running");
            Intent myintent = new Intent(context, background_service.class);
            context.startService(myintent);
        }


        }
    };

    @Override
    public void edit_reminder_m(boolean edit_reminder, String reminderkey) {

        Log.v("Gavin", "Main Activity - key " + reminderkey);
        loadedit_reminder(reminderkey);
    }



    public void Welcome_Done(Boolean finishedTTS){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Log.v("Gavin", "Check" + Boolean.toString(finishedTTS));
        if (finishedTTS.equals(Boolean.TRUE)){
            Log.v("Gavin", "Check" + Boolean.toString(finishedTTS));
            Menu_Fragment fragment_c = new Menu_Fragment();
            fragmentTransaction.replace(R.id.activity_main, fragment_c);
            fragmentTransaction.commit();
        }

    }

    public void logout(){

        GoogleSignInClient mGoogleSignInClient;

        AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Firebase_Authenitication_Fragment fragment = new Firebase_Authenitication_Fragment();
        fragmentTransaction.add(R.id.activity_main, fragment);
        fragmentTransaction.commit();
    }

    public void new_reminder(){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Set_New_Reminder_Fragment set_new_reminder_fragment = new Set_New_Reminder_Fragment();
        fragmentTransaction.replace(R.id.activity_main,set_new_reminder_fragment);
        fragmentTransaction.commit();
    }

    public void reminder_menu(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Reminder_Menu_Fragment reminder_menu_fragment = new Reminder_Menu_Fragment();
        fragmentTransaction.replace(R.id.activity_main, reminder_menu_fragment);
        fragmentTransaction.commit();

    }

    public void add_contact(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Add_contact_fragment add_contact_fragments = new Add_contact_fragment();
        fragmentTransaction.replace(R.id.activity_main, add_contact_fragments);
        fragmentTransaction.commit();
    }

    public void contacts_menu(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        contacts_menu_fragment Contacts_menu_fragment = new contacts_menu_fragment();
        fragmentTransaction.replace(R.id.activity_main, Contacts_menu_fragment);
        fragmentTransaction.commit();
    }

    public void loadedit_reminder(String reminder_key){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Edit_Reminder_Fragment edit_reminder_fragment = new Edit_Reminder_Fragment();

        Log.v("Gavin", "Main Activity - key 2 " + reminder_key);

        Bundle args = new Bundle();

        if (reminder_key != null) {
            args.putString(KEY_FILE, reminder_key);
            Log.v("Gavin", "Main Activity - key 3 " + reminder_key);
        }

        edit_reminder_fragment.setArguments(args);
        fragmentTransaction.replace(R.id.activity_main, edit_reminder_fragment);
        fragmentTransaction.commit();
    }

    private void createNotificationChannel(){

        CharSequence name = "nabigetonabi";
        String description = "nabi notification channel";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        String CHANNEL_ID = getString(R.string.Channel1);

        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);


    }

}