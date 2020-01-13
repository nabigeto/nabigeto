package com.nabigeto.gavin.nabigetonabi.CreateNewReminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.nabigeto.gavin.nabigetonabi.Notification_Module.notification_reciever;

import java.util.Calendar;

public class Set_reminder_alarm {


    public void start_alarms (reminder_model lmodel, Context context){

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
