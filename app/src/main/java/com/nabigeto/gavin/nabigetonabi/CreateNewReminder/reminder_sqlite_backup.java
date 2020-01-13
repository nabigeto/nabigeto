package com.nabigeto.gavin.nabigetonabi.CreateNewReminder;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.nabigeto.gavin.nabigetonabi.UtilitiesDB.Alarm_Restart_Contract;

public class reminder_sqlite_backup {

    private Integer daily_m;
    private Integer hourly_m;
    private Integer weekly_m;
    private Integer monthly_m;
    private Integer yearly_m;
    private Integer set_active_m;



    public void load_values(String key_reminder, Boolean daily, Integer year, Integer day, String userid, Integer hour, Boolean hourly, Boolean weekly,
                            Boolean monthly, Integer month, Integer minute, Boolean yearly, Boolean set_active, String reminder_content, String reminder_title,
                            Integer time, Context context){


        Log.v("Gavin", "Reminder_sqlite_update " + "1");

        if(hourly==Boolean.TRUE){
            hourly_m = 3;
        }
        else{
            hourly_m=2;
        }

        if(daily==Boolean.TRUE){
            daily_m = 3;
        }
        else{
            daily_m=2;
        }

        if(weekly==Boolean.TRUE){
            weekly_m = 3;
        }
        else{
            weekly_m=2;
        }

        if(monthly==Boolean.TRUE){
            monthly_m = 3;
        }
        else{
            monthly_m=2;
        }

        if(yearly==Boolean.TRUE){
            yearly_m = 3;
        }
        else{
            yearly_m=2;
        }

        if(set_active==Boolean.TRUE){
            set_active_m = 3;
        }
        else{
            set_active_m=2;
        }
        Log.v("Gavin", "Reminder_sqlite_update " + "2");

        ContentValues alarmValues = new ContentValues();
/**
 alarmValues.put(Alarm_Restart_Contract.AlarmInfo._ID, key_reminder);
 **/
        alarmValues.put(Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_USER_ID, userid);
        alarmValues.put(Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_ENTRY_ID, key_reminder);
        alarmValues.put(Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_DATE, year);
        alarmValues.put(Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_TIME, time);
        alarmValues.put(Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_REMINDER_TITLE, reminder_title);

        alarmValues.put(Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_YR, year);
        alarmValues.put(Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_MTH, month);
        alarmValues.put(Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_DY, day);
        alarmValues.put(Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_HR, hour);
        alarmValues.put(Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_MTE, minute);

        alarmValues.put(Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_YRLY, yearly_m);
        alarmValues.put(Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_MONTHLY, monthly_m);
        alarmValues.put(Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_WEEKLY, weekly_m);
        alarmValues.put(Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_DAILY, daily_m);
        alarmValues.put(Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_HOURLY, hourly_m);

        alarmValues.put(Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_SET_ACTIVE, set_active_m);
        alarmValues.put(Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_REMINDER_CONTENT, reminder_content);

    Uri movie_insert = context.getContentResolver().insert(
            Alarm_Restart_Contract.AlarmInfo.CONTENT_URI_F,
            alarmValues);

    }

}
