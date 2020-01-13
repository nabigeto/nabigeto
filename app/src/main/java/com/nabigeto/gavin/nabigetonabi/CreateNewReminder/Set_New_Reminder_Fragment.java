package com.nabigeto.gavin.nabigetonabi.CreateNewReminder;


import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nabigeto.gavin.nabigetonabi.R;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Set_New_Reminder_Fragment extends Fragment {


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

    public Boolean reminderstate = false;
    public Boolean toggle_time = false;
    public Boolean toggle_date = false;
    public Boolean reminder_live = false;

    public String mreminder_content = null;

    public String reminderid;

    public Boolean hourly;
    public Boolean daily;
    public Boolean weekly;
    public Boolean monthly;
    public Boolean yearly;

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

    Context context;

    public Set_New_Reminder_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        reminder_model reminderModel= new reminder_model();

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
                    hourly=Boolean.TRUE;
                }
                else{
                    hourly=Boolean.FALSE;
                }
            }
        });

        mdaily = rootview.findViewById(R.id.toggle_daily);
        mdaily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    daily=Boolean.TRUE;
                }
                else{
                    daily=Boolean.FALSE;
                }
            }
        });

        mweekly = rootview.findViewById(R.id.toggle_weekly);
        mweekly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    weekly=Boolean.TRUE;
                }
                else{
                    weekly=Boolean.FALSE;
                }
            }
        });

        mmonthly = rootview.findViewById(R.id.toggle_monthly);
        mmonthly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    monthly=Boolean.TRUE;
                }
                else{
                    monthly=Boolean.FALSE;
                }
            }
        });

        myearly = rootview.findViewById(R.id.toggle_yearly);
        myearly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    yearly=Boolean.TRUE;
                }
                else{
                    yearly= Boolean.FALSE;
                }
            }
        });

        toggle_live = rootview.findViewById(R.id.toggle_live);
        toggle_live.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (!reminder_live){
                    reminder_live = Boolean.TRUE;
                    reminder_alarm();
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

        return rootview;


    }


    public void rightreminder (){

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser != null){

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
            hour = timePicker.getCurrentHour();
            minute = timePicker.getCurrentMinute();
            mreminder_model.hour = hour;
            mreminder_model.minute = minute;
        }

        mreminder_model.reminder_title = remindertitle.getText().toString();
        mreminder_model.reminder_content = reminderbody.getText().toString();
        mreminder_model.user_id = userid;
        mreminder_model.set_active = reminder_live;
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


        reminder_sqlite_backup Reminder_sqlite_backup = new reminder_sqlite_backup();

            Reminder_sqlite_backup.load_values(key_reminder, daily, year, day, userid, hour, hourly, weekly, monthly, month, minute, yearly, reminder_live, reminderbody.getText().toString(), remindertitle.getText().toString(), hour, getContext());

        }

} else {

        Toast.makeText(getContext(), "Oh, there seems to be an error", Toast.LENGTH_LONG);
        }
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

    private void reminder_alarm (){

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
            hour = timePicker.getCurrentHour();
            minute = timePicker.getCurrentMinute();
            mreminder_model.hour = hour;
            mreminder_model.minute = minute;
        }

        mreminder_model.reminder_title = remindertitle.getText().toString();
        mreminder_model.reminder_content = reminderbody.getText().toString();
        mreminder_model.user_id = userid;
        mreminder_model.set_active = reminder_live;
        mreminder_model.hourly=hourly;
        mreminder_model.daily=daily;
        mreminder_model.weekly=weekly;
        mreminder_model.monthly=monthly;
        mreminder_model.yearly=yearly;

        context = getContext();

        Set_reminder_alarm set_reminder_alarm = new Set_reminder_alarm();

        set_reminder_alarm.start_alarms(mreminder_model, context);



    }



}
