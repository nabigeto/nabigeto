package com.nabigeto.gavin.nabigetonabi.UtilitiesDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gavin on 3/8/2016.
 */
public class Alarm_Restart_db_Helper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME ="alarm_restart_database_b.db";


    public Alarm_Restart_db_Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        final String SQL_CREATE_FAVOURITE_TABLE = "CREATE TABLE " + Alarm_Restart_Contract.AlarmInfo.TABLE_NAME_F + " (" +

                    Alarm_Restart_Contract.AlarmInfo._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                    Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_USER_ID + " TEXT NOT NULL, " +
                    Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_ENTRY_ID + " TEXT NOT NULL, " +
                    Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_DATE + " TEXT NOT NULL, " +
                    Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_TIME + " TEXT NOT NULL, " +
                    Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_REMINDER_TITLE + " TEXT NOT NULL, " +
                    Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_YR + " TEXT NOT NULL, " +
                    Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_MTH + " TEXT NOT NULL, " +
                    Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_DY + " TEXT NOT NULL, " +
                    Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_HR + " TEXT NOT NULL, " +
                    Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_MTE + " TEXT NOT NULL, " +
                    Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_YRLY + " INTEGER NOT NULL, " +
                    Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_MONTHLY + " INTEGER NOT NULL, " +
                    Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_WEEKLY + " INTEGER NOT NULL, " +
                    Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_HOURLY + " INTEGER NOT NULL, " +
                    Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_DAILY + " INTEGER NOT NULL, " +
                    Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_SET_ACTIVE + " INTEGER NOT NULL, " +
                    Alarm_Restart_Contract.AlarmInfo.COLUMN_NAME_REMINDER_CONTENT + " INTEGER NOT NULL " + ");";


            sqLiteDatabase.execSQL(SQL_CREATE_FAVOURITE_TABLE);
    }


    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Alarm_Restart_Contract.AlarmInfo.TABLE_NAME_F);

        onCreate(sqLiteDatabase);
    }

    public void onClear(SQLiteDatabase sqLiteDatabase){

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Alarm_Restart_Contract.AlarmInfo.TABLE_NAME_F);
    }

}
