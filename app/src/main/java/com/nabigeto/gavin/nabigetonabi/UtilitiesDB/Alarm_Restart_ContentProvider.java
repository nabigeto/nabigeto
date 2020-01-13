package com.nabigeto.gavin.nabigetonabi.UtilitiesDB;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Created by Gavin on 3/29/2016.
 */
public class Alarm_Restart_ContentProvider extends ContentProvider {
/**
    public static final UriMatcher sUriMatcher = buildUriMatcher();
**/
    private Alarm_Restart_db_Helper mOpenHelper;

    private static final UriMatcher sUriMatcher;


    private static final String AUTHORITY = Alarm_Restart_Contract.CONTENT_AUTHORITY_F;
    private static final String TABLE_NAME = "alarm_restart";
    static final int ALARM_INFO = 101;


    private static final SQLiteQueryBuilder sMovie_InfoQueryBuilder;

    static {

        sMovie_InfoQueryBuilder = new SQLiteQueryBuilder();

        sMovie_InfoQueryBuilder.setTables(
                Alarm_Restart_Contract.AlarmInfo.TABLE_NAME_F);


    }

    private static final String sMovie_InfoSettingSelection = Alarm_Restart_Contract.AlarmInfo.TABLE_NAME_F + " = ? ";

    
    private Cursor getMovie_Info(Uri uri, String[] projection, String sortOrder) {

        String Favourite_Info_Setting = Alarm_Restart_Contract.AlarmInfo.getAlarmNameFromUri(uri);


        String[] selectionArgs;
        String selection;

        selection = sMovie_InfoSettingSelection;
        selectionArgs = new String[]{Favourite_Info_Setting};


        return sMovie_InfoQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);


    }

    static {

        sUriMatcher = new UriMatcher(0);

        sUriMatcher.addURI(
                Alarm_Restart_ContentProvider.AUTHORITY,
                Alarm_Restart_Contract.AlarmInfo.TABLE_NAME_F,
                ALARM_INFO
        );

    }



    @Override
    public boolean onCreate(){
        mOpenHelper = new Alarm_Restart_db_Helper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {
        final int typeUri = sUriMatcher.match(uri);

        switch (typeUri) {

            case ALARM_INFO:
                return Alarm_Restart_Contract.AlarmInfo.CONTENT_TYPE_F;

            default:
                throw new UnsupportedOperationException("Unknown URI + 1" + uri);
        }
    }

        @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

            Cursor retCursor;

            String suri = uri.toString();

            final int typeUri = sUriMatcher.match(uri);

            switch (typeUri) {

                case ALARM_INFO: {

                    retCursor = mOpenHelper.getReadableDatabase().query(
                            Alarm_Restart_Contract.AlarmInfo.TABLE_NAME_F,
                            projection,
                            selection,
                            selectionArgs,
                            null,
                            null,
                            sortOrder

                    );

                    break;
                }

                default:
                    throw new UnsupportedOperationException("Unknown uri + 2b" + uri);
            }

            retCursor.setNotificationUri(getContext().getContentResolver(), uri);
            return retCursor;
        }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues){

        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;
        switch(match) {

            case ALARM_INFO: {

                long db_id = db.insert(Alarm_Restart_Contract.AlarmInfo.TABLE_NAME_F, null, contentValues);
                if (db_id > 0)
                    returnUri = Alarm_Restart_Contract.AlarmInfo.buildAlarm_InfoUri_F(db_id);
                else
                    throw new android.database.SQLException("Failed to insert row into" + uri);
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri + 3" + uri);

        }

        getContext().getContentResolver().notifyChange(uri, null);


        return returnUri;
    }

    @Override
    public int update(Uri uri, ContentValues contentvalues, String selection , String[] selectionArgs){
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsUpdated;

        switch(match){

            case ALARM_INFO:{
                rowsUpdated = db.update(Alarm_Restart_Contract.AlarmInfo.TABLE_NAME_F, contentvalues, selection, selectionArgs);
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri + 4" + uri);
        }

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    @Override
    public int delete(Uri uri, String selection, String [] selectionArgs){
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDeleted;

        if (null == selection) selection ="1";

        switch(match){

            case ALARM_INFO:{
                rowsDeleted = db.delete(Alarm_Restart_Contract.AlarmInfo.TABLE_NAME_F, selection, selectionArgs);
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri + 5" + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);


        return rowsDeleted;
    }


    @Override
    public int bulkInsert(Uri uri, ContentValues[] values){

        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        String smatch = Integer.toString(match);

        switch(match){
            case ALARM_INFO:
                db.beginTransaction();
                int returnCount = 0;
                try {
                    for (ContentValues value : values){
                        long _id = db.insert(Alarm_Restart_Contract.AlarmInfo.TABLE_NAME_F, null, value);
                        if(_id != -1) {
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                }
                finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
                return returnCount;
        }

        return super.bulkInsert(uri, values);

        }

    @Override
    public void shutdown() {
        mOpenHelper.close();
        super.shutdown();
    }


    public void close(){
        mOpenHelper.close();
    }


    }


