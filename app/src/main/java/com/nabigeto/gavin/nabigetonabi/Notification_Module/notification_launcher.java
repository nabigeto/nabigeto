package com.nabigeto.gavin.nabigetonabi.Notification_Module;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class notification_launcher {


    public void notification_builder (Context context, String name, String content){

    String CHANNEL_ID = "channel 1";
    String Title = name;
    String Content_text = content;
    Integer notificationID = Integer.parseInt(Title);



    NotificationCompat.Builder builder = new NotificationCompat.Builder(context , CHANNEL_ID)
            .setContentTitle(Title)
            .setContentText(Content_text)
            .setStyle(new NotificationCompat.BigTextStyle()
                    .bigText(Content_text))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(notificationID , builder.build());

    }


    public void notification_activate(Context context){


    }

}
