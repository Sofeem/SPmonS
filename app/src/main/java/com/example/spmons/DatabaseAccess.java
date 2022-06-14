package com.example.spmons;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getReadableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }
    public List<String> getQuotes() {


         List<String> list = new ArrayList<>();
         int count = 0;
        Cursor cursor = database.rawQuery("SELECT * FROM Sensor_Data_new", null);
        cursor.moveToFirst();
        while ( !cursor.isAfterLast()) {


            /*String actual = cursor.getString(9);
            String date  = actual.substring(0, 10);
            String time  = actual.substring(11, 16);
            cursor.getString(15);
            list.add( cursor.getString(15));
            list.add(date);
            list.add(time);
            list.add(cursor.getString(11));*/
            cursor.moveToNext();
            count++;
        }
        cursor.close();

        Log.d("D", "InRecordsize:" + cursor.getCount());
        return list;
    }

    public List<String[]> getData() {

        ArrayList<String[]> parkinginfolist = new ArrayList<String[]>();
        String[]  parkinginfo = new String[4];
        Cursor cursor = database.rawQuery("SELECT * FROM Sensor_Data_new", null);
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            String actual = cursor.getString(9);
            String date  = actual.substring(0, 10);
            String time  = actual.substring(11, 16);
            cursor.getString(15);
            parkinginfo[0] = cursor.getString(15);
            parkinginfo[1] = actual.substring(0, 10);
            parkinginfo[2] = actual.substring(11, 16);
            parkinginfo[1] = cursor.getString(15);

            parkinginfolist.add(parkinginfo);

            cursor.moveToNext();
        }
        cursor.close();


        return parkinginfolist;
    }

    /*private String getparkinglocation(String ps) {
        String parkinginfo = "";

        if (ps.equals("00-80-00-00-04-01-9f-d8")){
            parkinginfo = "PS3";
        }
        else if (ps.equals("00-80-00-00-04-01-a4-a2"))
        {
            parkinginfo = "PS1";
        }
        else if (ps.equals("00-80-00-00-04-01-a4-9f")){parkinginfo = "PS2";}
        else if (ps.equals("00-80-00-00-04-01-a4-8d")){parkinginfo = "PS4";}
        else if (ps.equals("00-80-00-00-04-01-a0-20")){parkinginfo = "PS5";}
        else if (ps.equals("00-80-00-00-04-01-a4-b7")){parkinginfo = "PS6";}
        else if (ps.equals("00-80-00-00-04-01-9f-db")){parkinginfo = "PS7";}
        else if (ps.equals("00-80-00-00-04-01-9f-ea")){parkinginfo = "PS9";}
        else if (ps.equals("00-80-00-00-04-01-a4-91"))
        {
            parkinginfo = "PS10";
        }



        return parkinginfo;
    }*/
}