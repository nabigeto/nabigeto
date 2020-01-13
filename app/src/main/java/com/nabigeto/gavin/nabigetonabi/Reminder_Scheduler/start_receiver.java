package com.nabigeto.gavin.nabigetonabi.Reminder_Scheduler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.nabigeto.gavin.nabigetonabi.Reminder_Scheduler.alarm_restart_service_a;

public class start_receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intenta = new Intent(context, alarm_restart_service_a.class);
        context.startService(intenta);

        Log.v("Gavin", "start_receiver - service to start alarms started");

        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
