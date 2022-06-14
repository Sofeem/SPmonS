package com.example.spmons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Dashboard extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    float values[] = {35,45};
    String mention[] = {"O","V"};
    String strDate;
    BarChart chart;
    int cV  = 0;
    int cO = 0;
    float OccRate;
    float occuV;
    float occuO;

    List<DataforParking> pinfo = new ArrayList<DataforParking>();
    List<String> St = new ArrayList<String>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        chart = findViewById(R.id.barchart);


        /// Getting Parking information
        //getData();


        //Draw the chart.
        drawBarChart();
        setupPieChart();
        //OccupancyRate();




        Button buttonOne = findViewById(R.id.Spl);
        buttonOne.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                System.out.println("Button Clicked");


                Intent activity2Intent = new Intent(getApplicationContext(), ParkingSpotInfo.class);
                startActivity(activity2Intent);
            }
        });



    }
    private void setupPieChart() {

        List<PieEntry> Pieentries = new ArrayList<>();
        for(int i = 0; i<values.length;i++){
            Pieentries.add(new PieEntry(values[i],mention[i]));

            PieDataSet DataSet = new PieDataSet(Pieentries,"");
            DataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            PieData data = new PieData(DataSet);

            PieChart chart = (PieChart) findViewById(R.id.PieChart);

            chart.setData(data);
            chart.animateY(1000);
            chart.invalidate();
        }
    }



    private void drawBarChart() {



        ArrayList<String>  pslist = new ArrayList<String> ();
        ArrayList OccupancyRate = new ArrayList();
        String[] Dbreference = new String[]{"00-80-00-00-04-01-a4-9f","00-80-00-00-04-01-a4-a2","00-80-00-00-04-01-9f-d8","00-80-00-00-04-01-a4-8d","00-80-00-00-04-01-a0-20",
                "00-80-00-00-04-01-a4-b7","00-80-00-00-04-01-9f-db","00-80-00-00-04-01-9f-d7","00-80-00-00-04-01-9f-ea","00-80-00-00-04-01-a4-91"};
        int[] data1 = new int[]{42,55,45,26,50,36,50,80,25,74};


        pslist.add("PS1");
        pslist.add("PS2");
        pslist.add("PS3");
        pslist.add("PS4");
        pslist.add("PS5");
        pslist.add("PS6");
        pslist.add("PS7");
        pslist.add("PS8");
        pslist.add("PS9");
        pslist.add("PS10");

         for(int i = 0; i < Dbreference.length;i++){
             float or = getOccupancyRate(Dbreference[i]);
             OccupancyRate.add(new BarEntry(i,data1[i]));

        }
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(pslist));
        xAxis.setGranularity(0.5f);
        xAxis.setGranularityEnabled(true);
        BarDataSet bardataset = new BarDataSet(OccupancyRate, "Rate");
        chart.animateY(500);
        BarData data = new BarData(bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.setData(data);



    }

    private  float getOccupancyRate(String path){


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(path);


        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds: snapshot.getChildren()){
                    Date ddate = Calendar.getInstance().getTime();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                    strDate = dateFormat.format(ddate);
                    String Time = snapshot.child(ds.getKey()).child("Time").getValue().toString();
                    String date  = Time.substring(0, 10);
                    String time  = Time.substring(11, 16);
                    //Log.d("D", "Data:" + strDate);
                    int i = 0;
                    String Status = "";
                    String P1key = ds.getKey();
                    String ParkingSpot = "PS1";



                    if (date.equals("2021-06-10")){
                        String Statusvalue = snapshot.child(ds.getKey()).child("Parking_status").getValue().toString();
                        St.add(Statusvalue);}


                    //pinfo.add(new DataforParking(ParkingSpot,date,time,Status,Statusvalue));
                    //Collections.sort(pinfo,Collections.reverseOrder());



                }

                occuV = Collections.frequency(St, "0");
                occuO = Collections.frequency(St, "1");
                OccRate = (occuO/occuV)*100;
                Log.d("D", "Data22:" + occuV);
                Log.d("D", "Data32:" + occuO);
                Log.d("D", "OR:" + OccRate );

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });














        return OccRate;
    }


}
