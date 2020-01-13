package com.nabigeto.gavin.nabigetonabi.Reminder_Scheduler;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.nabigeto.gavin.nabigetonabi.Notification_Module.notification_reciever;
import com.nabigeto.gavin.nabigetonabi.UtilitiesDB.Alarm_Restart_ContentProvider;
import com.nabigeto.gavin.nabigetonabi.UtilitiesDB.Alarm_Restart_Contract;
import com.nabigeto.gavin.nabigetonabi.UtilitiesDB.Alarm_Restart_db_Helper;

import java.util.Calendar;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class alarm_restart_service_a extends IntentService {

    Context context;

    public static final String [] Alarm_Columns = {
            Alarm_Restart_Contract.AlarmInfo.TABLE_NAME_F + "." +
            Alarm_Restart_Contract.AlarmInfo._ID,
            Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_USER_ID,
            Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_ENTRY_ID,
            Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_DATE,
            Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_TIME,
            Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_REMINDER_TITLE,
            Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_YRLY,
            Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_MONTHLY,
            Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_WEEKLY,
            Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_DAILY,
            Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_HOURLY,
            Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_YR,
            Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_MTH,
            Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_DY,
            Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_HR,
            Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_MTE,
            Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_REMINDER_CONTENT,
            Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_SET_ACTIVE
    };

    public final static int _ID = 0;
    public final static int USER_ID = 1;
    public final static int ENTRY_ID = 2;
    public final static int DATE = 3;
    public final static int TIME = 4;
    public final static int REMINDER_TITLE = 5;
    public final static int YRLY = 6;
    public final static int MONTHLY = 7;
    public final static int WEEKLY = 8;
    public final static int DAILY = 9;
    public final static int HOURLY = 10;
    public final static int YR = 11;
    public final static int MTH = 12;
    public final static int DY = 13;
    public final static int HR = 14;
    public final static int MTE = 15;
    public final static int REMINDER_CONTENT = 16;
    public final static int SET_ACTIVE = 17;





    live_reminder_model Live_reminder = new live_reminder_model();


    public alarm_restart_service_a() {
        super("alarm_restart_service_a");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {

            context = getApplicationContext();

            sql_alarm_reader(context);

        }
    }

    public void sql_alarm_reader (Context contexta){

        String query_value[] = {String.valueOf(2)};

        Log.v("Gavin", "alarm_restart_service_a" + " sql reader 1");

        Alarm_Restart_db_Helper alarm_restart_db_helper = new Alarm_Restart_db_Helper(contexta);

        Alarm_Restart_ContentProvider alarm_restart_contentProvider = new Alarm_Restart_ContentProvider();

        Log.v("Gavin", "alarm_restart_service_a" + " sql reader 2");

        Cursor cursor = alarm_restart_contentProvider.query(Alarm_Restart_Contract.AlarmInfo.CONTENT_URI_F, Alarm_Columns, Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_SET_ACTIVE, query_value , null);

        while (cursor.moveToNext()){
            int n_date = cursor.getColumnIndex(Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_DAILY);
             Live_reminder.date = cursor.getString(n_date);

             int n_time = cursor.getColumnIndex(Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_TIME);
             Live_reminder.mtime = cursor.getString(n_time);



            int n_yearly = cursor.getColumnIndex(Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_YRLY);
            if (cursor.getInt(n_yearly) == 3){
                Live_reminder.yearly = true;
            }
            else{
                Live_reminder.yearly = false;
            };

            int n_monthly = cursor.getColumnIndex(Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_MONTHLY);
            if (cursor.getInt(n_monthly) == 3){
                Live_reminder.monthly = true;
            }
            else{
                Live_reminder.monthly = false;
            };

            int n_weekly = cursor.getColumnIndex(Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_WEEKLY);
            if (cursor.getInt(n_weekly) == 3){
                Live_reminder.weekly = true;
            }
            else{
                Live_reminder.weekly = false;
            };

            int n_daily = cursor.getColumnIndex(Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_DAILY);
            if (cursor.getInt(n_monthly) == 3){
                Live_reminder.daily = true;
            }
            else{
                Live_reminder.daily = false;
            };

            int n_hourly = cursor.getColumnIndex(Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_HOURLY);
            if (cursor.getInt(n_hourly) == 3){
                Live_reminder.hourly = true;
            }
            else{
                Live_reminder.hourly = false;
            };

            int n_yr = cursor.getColumnIndex(Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_YR);
            Live_reminder.year = cursor.getInt(n_yr);

            int n_mth = cursor.getColumnIndex(Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_MTH);
            Live_reminder.month = cursor.getInt(n_yr);

            int n_day = cursor.getColumnIndex(Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_DY);
            Live_reminder.day = cursor.getInt(n_day);

            int n_hr = cursor.getColumnIndex(Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_HR);
            Live_reminder.hour = cursor.getInt(n_hr);

        start_alarms(Live_reminder);


        }

    }

    private void start_alarms (live_reminder_model lmodel){

        AlarmManager alarmManager;
        PendingIntent pendingIntent;

        alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, notification_reciever.class);
        intent.putExtra("title", lmodel.reminder_title);
        intent.putExtra("content", lmodel.reminder_content);
        pendingIntent = PendingIntent.getBroadcast(context,0,intent,0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        calendar.set(Calendar.YEAR, lmodel.year);
        calendar.set(Calendar.MONTH,lmodel.month);
        calendar.set(Calendar.DAY_OF_MONTH,lmodel.day);
        calendar.set(Calendar.HOUR_OF_DAY, lmodel.hour);
        calendar.set(Calendar.MINUTE, lmodel.minute);



        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000*60*20, pendingIntent);


    }




}
