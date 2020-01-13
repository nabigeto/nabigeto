package com.nabigeto.gavin.nabigetonabi.UtilitiesDB;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by Gavin on 3/8/2016.
 */
public class Alarm_Restart_Contract {


    public static final String CONTENT_AUTHORITY_F = ("com.nabigeto.gavin.nabigetonabi.alarm_restart.provider");

    public static final Uri BASE_CONTENT_URI_F = Uri.parse("content://" + CONTENT_AUTHORITY_F);

    public static final String PATH_MOVIE_F = "alarm_restart";



    public static final class AlarmInfo implements BaseColumns {

        public static final Uri CONTENT_URI_F = BASE_CONTENT_URI_F.buildUpon().appendPath(PATH_MOVIE_F).build();

        public static final String CONTENT_TYPE_F = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY_F + "/" + PATH_MOVIE_F;

        public static final String CONTENT_ITEM_TYPE_F = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY_F + "/" + PATH_MOVIE_F;



/**
         *     public String user_id;
         *     public String date;
         *     public String mtime;
         *     public String reminder_title;
         *
         *     public int year;
         *     public int month;
         *     public int day;
         *
         *     public int hour;
         *     public int minute;
         *
         *     public Boolean hourly;
         *     public Boolean daily;
         *     public Boolean weekly;
         *     public Boolean monthly;
         *     public Boolean yearly;
         *
         *     public Boolean set_active;
         *
         *     public String reminder_content;
         *
         */


        public static final String TABLE_NAME_F = "alarm_restart";

        public static final String _ID = "_id";
        public static final String COLUMN_NAME_ENTRY_ID = "entry_id";
        public static final String COLUMN_NAME_USER_ID = "userid";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_TIME = "time";
        public static final String COLUMN_NAME_REMINDER_TITLE = "reminder_title";

        public static final String COLUMN_NAME_YR = "year";
        public static final String COLUMN_NAME_MTH = "month";
        public static final String COLUMN_NAME_DY = "day";
        public static final String COLUMN_NAME_HR = "hour";
        public static final String COLUMN_NAME_MTE = "minute";

        public static final String COLUMN_NAME_HOURLY = "Hourly";
        public static final String COLUMN_NAME_DAILY = "Daily";
        public static final String COLUMN_NAME_WEEKLY = "Weekly";

        public static final String COLUMN_NAME_MONTHLY = "Monthly";
        public static final String COLUMN_NAME_YRLY = "Yearly";
        public static final String COLUMN_NAME_SET_ACTIVE = "SetActive";

        public static final String COLUMN_NAME_REMINDER_CONTENT = "reminder_content";


        public static Uri buildAlarm_InfoUri_F(long id) {

            return CONTENT_URI_F.buildUpon().appendPath(String.valueOf(id)).build();

        }

        public static String getAlarmNameFromUri(Uri uri) {

            return uri.getPathSegments().get(1);

        }


    }
}