package com.nabigeto.gavin.nabigetonabi.Notification_Module;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.nabigeto.gavin.nabigetonabi.Notification_Module.notification_launcher;

public class notification_reciever extends BroadcastReceiver {

public String name;
public String content;

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle extras = intent.getExtras();

        name = extras.getString("title");
        content = extras.getString("content");

        notification_launcher Notification_launcher = new notification_launcher();
        Notification_launcher.notification_builder(context, name, content);

    }
}
