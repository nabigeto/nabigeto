package com.nabigeto.gavin.nabigetonabi.EditReminder;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nabigeto.gavin.nabigetonabi.CreateNewReminder.reminder_model;
import com.nabigeto.gavin.nabigetonabi.R;

import java.util.Calendar;

/**
 * Created by Gavin on 8/4/2018.
 */

public class Edit_Reminder_Fragment extends Fragment {

    public DatabaseReference mDatabase;

    public int hour;
    public int minute;

    public int year;
    public int month;
    public int day;

    public String muser_id = null;
    public String mdate = null;
    public String mmmtime = null;

    public String userid;

    public boolean reminderstate = false;
    public boolean toggle_time = false;
    public boolean toggle_date = false;
    public boolean reminder_live = false;

    public String mreminder_content = null;

    public String reminderid;

    public boolean hourly;
    public boolean daily;
    public boolean weekly;
    public boolean monthly;
    public boolean yearly;

    public Button savereminderbutton;
    public Button reminder_time;
    public Button reminder_date;

    public ToggleButton toggle_live;
    public ToggleButton mhourly;
    public ToggleButton mdaily;
    public ToggleButton mweekly;
    public ToggleButton mmonthly;
    public ToggleButton myearly;

    public TextView remindertitle;
    public TextView reminderbody;

    public TimePicker timePicker;
    public DatePicker datePicker;

    private Calendar calendar;

    private boolean t;

    public static final String KEY_FILE = "KEY";
    public String key;

    edit_adapter_model Edit_model;

    public Edit_Reminder_Fragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        reminder_model reminderModel= new reminder_model();

        Bundle args = getArguments();

        key = args.getString(KEY_FILE, key);

        Log.v("Gavin", "Edit_Reminder_Fragment - key " + key);

        calendar = Calendar.getInstance();

        Log.v("Gavin", "Set New Reminder Fragment + creating");

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        View rootview = inflater.inflate(R.layout.fragment_set__new__reminder_ , container, false);

        remindertitle = rootview.findViewById(R.id.reminder_title);
        reminderbody = rootview.findViewById(R.id.reminder_comment);

        timePicker = rootview.findViewById(R.id.reminder_time);
        datePicker = rootview.findViewById(R.id.reminder_date);




          reminder_time = rootview.findViewById(R.id.toggle_time);






        reminder_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!toggle_time){
                    toggle_time = true;
                }
                time_picker();

            }
        });

        reminder_date = rootview.findViewById(R.id.toggle_date);
        reminder_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!toggle_date){
                    toggle_date = true;
                }
                date_picker();

            }
        });

        mhourly = rootview.findViewById(R.id.toggle_hourly);
        mhourly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    hourly=true;
                }
                else{
                    hourly=false;
                }
            }
        });

        mdaily = rootview.findViewById(R.id.toggle_daily);
        mdaily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    daily=true;
                }
                else{
                    daily=false;
                }
            }
        });

        mweekly = rootview.findViewById(R.id.toggle_weekly);
        mweekly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    weekly=true;
                }
                else{
                    weekly=false;
                }
            }
        });

        mmonthly = rootview.findViewById(R.id.toggle_monthly);
        mmonthly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    monthly=true;
                }
                else{
                    monthly=false;
                }
            }
        });

        myearly = rootview.findViewById(R.id.toggle_yearly);
        myearly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    yearly=true;
                }
                else{
                    yearly= false;
                }
            }
        });

        toggle_live = rootview.findViewById(R.id.toggle_live);
        toggle_live.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (!reminder_live){
                    reminder_live = true;
                }
            }
        });

        savereminderbutton = rootview.findViewById(R.id.reminder_save);
        savereminderbutton.setOnClickListener(new View.OnClickListener() {
                                                  public void onClick(View v) {

                                                      if (!reminderstate){
                                                          reminderstate = true;
                                                      }

                                                      rightreminder();
                                                  }
                                              }
        );

        readreminder();

        return rootview;


    }

    public void readreminder(){


        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        userid = currentUser.getUid();

        Log.v("Gavin", "Edit Reminder Fragment - Getting user" + userid);
        Log.v("Gavin", "Edit Reminder fragment - path key " + key);

        mDatabase  = FirebaseDatabase.getInstance().getReference().child("reminder").child(userid).child(key);

        Log.v("Gavin", "Edit Reminder Fragment - checking ");

        if (mDatabase == null){
            Log.v("Gavin", "Edit Reminder Fragment " + "Error reference is null");

        }
        else {
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Edit_model = dataSnapshot.getValue(edit_adapter_model.class);


                    remindertitle.setText(Edit_model.reminder_title);
                    reminderbody.setText(Edit_model.reminder_content);

                    timePicker.setHour(Edit_model.hour);
                    timePicker.setMinute(Edit_model.minute);

                    datePicker.updateDate(Edit_model.year, Edit_model.month, Edit_model.day);
                    reminder_time.setText(Edit_model.mtime);
                    reminder_date.setText(Edit_model.date);

                    if (Edit_model.hourly) {
                    mhourly.setChecked(true);
                    }
                   else {
                        mhourly.setChecked(false);
                    }

                    if (Edit_model.daily) {
                        mdaily.setChecked(true);
                    }
                    else {
                        mdaily.setChecked(false);
                    }

                    if (Edit_model.weekly) {
                        mweekly.setChecked(true);
                    }
                    else {
                        mweekly.setChecked(false);
                    }

                    if (Edit_model.monthly) {
                        mmonthly.setChecked(true);
                    }
                    else {
                        mmonthly.setChecked(false);
                    }

                    if (Edit_model.yearly) {
                        myearly.setChecked(true);
                    }
                    else {
                        myearly.setChecked(false);
                    }

                    if (Edit_model.set_active) {
                        toggle_live.setChecked(true);
                    }
                    else {
                        toggle_live.setChecked(false);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }
    }

    public void rightreminder (){

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        userid = firebaseUser.getUid();


        Log.v("Gavin", "Set New Reminder Fragment " + userid);

        reminder_model mreminder_model = new reminder_model();

        if(!toggle_date){
            year = datePicker.getYear();
            month = datePicker.getMonth();
            day = datePicker.getDayOfMonth();
            mreminder_model.year = year;
            mreminder_model.month = month;
            mreminder_model.day = day;
        }

        if(!toggle_time){
            hour = timePicker.getHour();
            minute = timePicker.getMinute();
            mreminder_model.hour = hour;
            mreminder_model.minute = minute;
        }

        mreminder_model.reminder_title = remindertitle.getText().toString();
        mreminder_model.reminder_content = reminderbody.getText().toString();
        mreminder_model.user_id = userid;
        mreminder_model.set_active = true;
        mreminder_model.hourly=hourly;
        mreminder_model.daily=daily;
        mreminder_model.weekly=weekly;
        mreminder_model.monthly=monthly;
        mreminder_model.yearly=yearly;


        DatabaseReference mfirebasedatabase = FirebaseDatabase.getInstance().getReference();

        mfirebasedatabase.child("reminder").child(userid);

        String key_reminder = mfirebasedatabase.child("reminder").child(userid).push().getKey();

        mfirebasedatabase.child("reminder").child(userid).child(key_reminder).setValue(mreminder_model);


        if (!reminder_live){

            mfirebasedatabase.child("livereminder").child(userid);

            String key_setlivereminder = mfirebasedatabase.child("livereminder").child(userid).push().getKey();

            mfirebasedatabase.child("livereminder").child(userid).child(key_setlivereminder).setValue(mreminder_model);

        }

        reminder_sqlite_update Reminder_sqlite_update = new reminder_sqlite_update();

        Reminder_sqlite_update.load_values(key_reminder, daily, year, day, userid, hour, hourly, weekly, monthly, month, minute, yearly, reminder_live, reminderbody.getText().toString(), remindertitle.getText().toString(), hour, getContext());


    }


    public void time_picker(){
        timePicker.findViewById(R.id.reminder_time);
        timePicker.setHour(calendar.get(Calendar.HOUR));
        timePicker.setMinute(calendar.get(Calendar.MINUTE));

    }

    public void date_picker(){
        datePicker.findViewById(R.id.reminder_date);
        datePicker.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

    }


}