package com.example.spmons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;



public class ParkingSpotInfo extends AppCompatActivity {
    ListView listView;
    ArrayList<DataforParking> PF = new ArrayList<>();
    Bundle bundle = new Bundle();
    String Date;

    CalendarView calendarView;
    TextView date_view;
    ArrayList<DataforParking> PF1 = new ArrayList<>();
    // creating a variable for
    // our Firebase Database.


    // creating a variable for our
    // Database Reference for Firebase.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_spot_info);
        this.listView = (ListView) findViewById(R.id.listview);
        //CustomAdapter arrayAdapter = new CustomAdapter(this,R.layout.parkinghistorylistview, PF);
        Intent activity2Intent = new Intent(getApplicationContext(), FirebaseDataAccess.class);
        calendarView = (CalendarView)
                findViewById(R.id.calendarView);
        date_view = (TextView)
                findViewById(R.id.dateview);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                 Date = i2 + "-"
                        + (i1 + 1) + "-" + i;

                // set this date in TextView for Display
                date_view.setText(Date);
                Log.d("Date", "Data:" + Date);
            }
        });

        /*DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<String> quotes = databaseAccess.getQuotes();
        databaseAccess.close();*/

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quotes);
       //this.listView.setAdapter(adapter);
        //Log.d("D", "Data:" + quotes.get(0));

        // below line is used to get the instance
        // of our Firebase database.


        // below line is used to get
        // reference for our database.
        //databaseReference = firebaseDatabase.getReference("00-80-00-00-04-01-9f-d7");

        // initializing our object class variable.


        // calling method
        // for getting data.
       // getdata();


        bundle.putString("date", Date );

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(ParkingSpotInfo.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 1) {
                  String Psdeviceid = "00-80-00-00-04-01-a4-9f";
                  String pspot = "PS1";
                  bundle.putString("Pdeviceid", Psdeviceid);
                  bundle.putString("parkingspot", pspot );



//Add the bundle to the intent
                    activity2Intent.putExtras(bundle);

                  startActivity(activity2Intent);
                } else if (i == 2) {
                    String Ps = "00-80-00-00-04-01-a4-a2";
                    String pspot = "PS2";
                    bundle.putString("Pdeviceid", Ps);
                    bundle.putString("parkingspot", pspot);

//Add the bundle to the intent
                    activity2Intent.putExtras(bundle);

                    startActivity(activity2Intent);

                } else if (i == 3) {
                    String Ps = "00-80-00-00-04-01-9f-d8";
                    String pspot = "PS3";
                    bundle.putString("Pdeviceid", Ps);
                    bundle.putString("parkingspot", pspot);

//Add the bundle to the intent
                    activity2Intent.putExtras(bundle);

                    startActivity(activity2Intent);

                } else if (i == 4) {
                    String Ps = "00-80-00-00-04-01-a4-8d";
                    String pspot = "PS4";
                    bundle.putString("Pdeviceid", Ps);
                    bundle.putString("parkingspot", pspot);

//Add the bundle to the intent
                    activity2Intent.putExtras(bundle);

                    startActivity(activity2Intent);
                }
                else if (i == 5) {
                    String Ps = "00-80-00-00-04-01-a0-20";
                    String pspot = "PS5";
                    bundle.putString("Pdeviceid", Ps);
                    bundle.putString("parkingspot", pspot);

//Add the bundle to the intent
                    activity2Intent.putExtras(bundle);

                    startActivity(activity2Intent);
                }
                else if (i == 6) {
                    String Ps = "00-80-00-00-04-01-a4-b7";
                    String pspot = "PS6";
                    bundle.putString("Pdeviceid", Ps);
                    bundle.putString("parkingspot", pspot);

//Add the bundle to the intent
                    activity2Intent.putExtras(bundle);

                    startActivity(activity2Intent);
                }
                else if (i == 7) {
                    String Ps = "00-80-00-00-04-01-9f-db";
                    String pspot = "PS7";
                    bundle.putString("Pdeviceid", Ps);
                    bundle.putString("parkingspot", pspot);

//Add the bundle to the intent
                    activity2Intent.putExtras(bundle);

                    startActivity(activity2Intent);
                }
                else if (i == 8) {
                    String Ps = "00-80-00-00-04-01-9f-d7";
                    String pspot = "PS8";
                    bundle.putString("Pdeviceid", Ps);
                    bundle.putString("parkingspot", pspot);

//Add the bundle to the intent
                    activity2Intent.putExtras(bundle);

                    startActivity(activity2Intent);
                }
                else if (i == 9) {
                    String Ps = "00-80-00-00-04-01-9f-ea";
                    String pspot = "PS9";
                    bundle.putString("Pdeviceid", Ps);
                    bundle.putString("parkingspot", pspot);

//Add the bundle to the intent
                    activity2Intent.putExtras(bundle);

                    startActivity(activity2Intent);
                }
                else if (i == 10) {
                    String Ps = "00-80-00-00-04-01-a4-91";
                    String pspot = "PS10";
                    bundle.putString("Pdeviceid", Ps);
                    bundle.putString("parkingspot", pspot);

//Add the bundle to the intent
                    activity2Intent.putExtras(bundle);

                    startActivity(activity2Intent);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }






}
