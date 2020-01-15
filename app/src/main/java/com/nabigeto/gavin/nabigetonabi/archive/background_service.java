package com.nabigeto.gavin.nabigetonabi.archive;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;

import androidx.annotation.Nullable;

import androidx.recyclerview.widget.RecyclerView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;


import java.util.ArrayList;

import static androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance;


public class background_service extends Service {

    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;

    public ArrayList arrayList;

    public static final String My_Prefs = "My_apps_prefs";
    public static final String SERVICE_ACTIVE = background_service.class.getName() + "ACTIVE";
    public static final String SERVICE_ACTION = background_service.class.getName() + "ACTION";

    public String user_id;

    public String date;
    public String reminder_title;
    public String reminder_content;
    public String mtime;
    public int year;
    public int month;
    public int day;
    public int hour;
    public int minute;




    public DatabaseReference mReference;
    public RecyclerView mrecyclerview;

    public PendingIntent pendingIntent;
    public AlarmManager alarmmanager;

    SharedPreferences sharedPreferences;

    private int key_s;
    private final static String PREFS = "MY App Data";



    private final class ServiceHandler extends Handler {

    public ServiceHandler(Looper looper){
        super(looper);
    }

    @Override
    public void handleMessage(Message msg){

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        user_id = currentUser.getUid();

        Log.v("Gavin", "background_service - Getting user" + user_id);


    }



    }


    @Override
    public void onCreate() {
        super.onCreate();
        HandlerThread thread = new HandlerThread("ServicesStarArguments", Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);


    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        Log.v("Gavin", "background_service" + " starting");
        getInstance(this).registerReceiver(mReceiver, new IntentFilter("LIVE"));
        Message msg;

        msg = mServiceHandler.obtainMessage();

        msg.arg1 = startId;

        mServiceHandler.sendMessage(msg);

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mReceiver);
        Toast.makeText(this, "Service Shutting Down", Toast.LENGTH_LONG).show();;
    }


    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(SERVICE_ACTION)){
                Log.v("Gavin", "background_service" + "bouncing live");
                LocalBroadcastManager manager = LocalBroadcastManager.getInstance(getApplicationContext());
                manager.sendBroadcast(new Intent(SERVICE_ACTIVE));
            }
        }
    };



/**

    public void new_reminder(live_reminder_model Live_Reminder_Model, String key){


        year = Live_Reminder_Model.year;
        month = Live_Reminder_Model.month;
        hour = Live_Reminder_Model.hour;
        day = Live_Reminder_Model.day;
        reminder_title = Live_Reminder_Model.reminder_title;
        date = Live_Reminder_Model.date;
        minute = Live_Reminder_Model.minute;
        mtime = Live_Reminder_Model.mtime;
        reminder_content = Live_Reminder_Model.reminder_content;
        user_id = Live_Reminder_Model.user_id;

        key_s = Integer.valueOf(key);

        Log.v("Gavin", "background_service " + key_s);


        if (!Live_Reminder_Model.hourly){


            Intent reminder_alarm = new Intent(getApplicationContext(), BootReceiver_j.class);

            pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), key_s, reminder_alarm, 0);

            alarmmanager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_HOUR, AlarmManager.INTERVAL_HOUR, pendingIntent);

        }
        if(!Live_Reminder_Model.daily){

            key_s = Integer.valueOf(key);

            Log.v("Gavin", "background_service " + key_s);

            Intent reminder_alarm = new Intent(getApplicationContext(), BootReceiver_j.class);

            pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), key_s, reminder_alarm, 0);

            alarmmanager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_DAY, AlarmManager.INTERVAL_DAY, pendingIntent);


        }
        if(!Live_Reminder_Model.weekly){

            key_s = Integer.valueOf(key);

            Log.v("Gavin", "background_service " + key_s);

            Intent reminder_alarm = new Intent(getApplicationContext(), BootReceiver_j.class);

            pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), key_s, reminder_alarm, 0);

            alarmmanager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + (AlarmManager.INTERVAL_DAY*30), (AlarmManager.INTERVAL_HOUR*30), pendingIntent);


        }
        if(!Live_Reminder_Model.monthly){

            key_s = Integer.valueOf(key);

            Log.v("Gavin", "background_service " + key_s);

            Intent reminder_alarm = new Intent(getApplicationContext(), BootReceiver_j.class);

            pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), key_s, reminder_alarm, 0);

            alarmmanager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_HOUR, AlarmManager.INTERVAL_HOUR, pendingIntent);

        }


    }

    public void remove_reminder(live_reminder_model Live_Reminder_Model, String key){

        key_s = Integer.valueOf(key);

        Log.v("Gavin", "background_service " + key_s);

        Intent reminder_alarm = new Intent(getApplicationContext(), BootReceiver_j.class);

        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), key_s, reminder_alarm, 0);

        alarmmanager.cancel(pendingIntent);

    }

    public void stored_reminders(){

        Context context = getApplicationContext();

        SharedPreferences sharedprefs = context.getSharedPreferences(My_Prefs ,MODE_PRIVATE);

    }


**/


/**
    private void sharedpref_save(String key, int key_s){

        sharedPreferences = getSharedPreferences(PREFS, MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, key_s);
        editor.apply();

        Log.v("Gavin", "background - sharedpref_save " + key + key_s);

    }

    private int sharedpref_read(String key, int key_s){

        sharedPreferences = getSharedPreferences(PREFS, MODE_PRIVATE){

            int key_ss = sharedPreferences.getInt(key,);

            return key_ss;
        }
    }

**/

/**
 *
 * specific date one off
 * specific time one off
 *
 * hour one off
 * hourly from now
 * hourly from specific time (assume from current date)
 * hourly from specific date (set from 8 am)
 *
 * daily one off
 * daily from now
 * daily from specific time (assume from current date)
 * daily from specific date (set from 8 am)
 *
 * weekly one off
 * weekly from now
 * weekly from specific time (assume current date)
 * weekly from specific date (set from 8 am)
 *
 * monthly one off
 * monthly from now
 * monthly from specific time (assume current date)
 * monthly from specific date (set from 8 am)
 *
 * yearly one off
 * yearly from now
 * yearly from specific time (assume current date)
 * yearly from specific date (set from 8 am)
 *
 */

}
