package com.example.spmons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FirebaseDataAccess extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<DataforParking> PF = new ArrayList<>();
    List<String> axisData = new ArrayList<String>();
    List<Integer> yAxisData = new ArrayList<Integer> ();
    //String[] axisData  = new String[]{};
    //int[] yAxisData  = new int[]{};
    ListView listView;
    LineChartView lineChartView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_data_access);
        Bundle bundle = getIntent().getExtras();
        String device_id = bundle.getString("Pdeviceid");
        String ps = bundle.getString("parkingspot");
        String d = bundle.getString("date");
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(device_id);
        this.listView = (ListView) findViewById(R.id.listview);
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,PF);
        CustomAdapter cs = new CustomAdapter(this, R.layout.parkinghistorylistview,PF);
        //lineChartView = findViewById(R.id.chart);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds: snapshot.getChildren()){
                    int i = 0;
                    String Status = "";
                    String P1key = ds.getKey();
                    String ParkingSpot = ps;
                    String Time = snapshot.child(ds.getKey()).child("Time").getValue().toString();
                    String date  = Time.substring(0, 10);
                    String time  = Time.substring(11, 16);
                    axisData.add(time);


                   String Statusvalue = snapshot.child(ds.getKey()).child("Parking_status").getValue().toString();
                    yAxisData.add(Integer.parseInt(Statusvalue));
                    if (Statusvalue.equals("0")){Status = "Vacant";} else if (Statusvalue.equals("1")){Status = "Occupied";}

                    PF.add(new DataforParking(ParkingSpot,date,time,Status,Statusvalue));
                    Collections.sort(PF,Collections.reverseOrder());
                }
                    listView.setAdapter(cs);


              /*  List yAxisValues = new ArrayList();
                List axisValues = new ArrayList();
                Line line = new Line(yAxisValues);


                for(int i = axisData.size()-1; i > (axisData.size() - 12) ; i--){
                    int j = 0;
                    axisValues.add(j, new AxisValue(j).setLabel(axisData.get(i)));
                    j++;
                    Log.d("D", "Data:" + axisData.get(i));
                    Log.d("D", "Size:" + i);
                }

                for(int a = yAxisData.size()-1; a > (yAxisData.size() - 12) ; a--){
                    int e = 0;
                    yAxisValues.add(new PointValue(e, yAxisData.get(a)));
                    e = e+1;
                    Log.d("D", "yData:" + yAxisData.get(a));
                    Log.d("D", "c:" + yAxisValues.get(e-1));
                    Log.d("D", "Size:" + a);
                }



                List lines = new ArrayList();
                lines.add(line);

                LineChartData data = new LineChartData();
                data.setLines(lines);

                lineChartView.setLineChartData(data);



                Axis axis = new Axis();
                axis.setValues(axisValues);
                data.setAxisXBottom(axis);

                Axis yAxis = new Axis();
                data.setAxisYLeft(yAxis);*/



                    //PF.add(Time);
                    //PF.add(Status);

                    //cs.notifyDataSetChanged();

                   // Log.d("D", "Data:" + ds.getKey());
                    //Log.d("D", "Data:" + Time); */

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        //drawLineChart();


    }


    public void drawLineChart() {



        /*String[] axisData = new String[]{};
        int[] yAxisData  = new int[]{};



        for (int i = 0; i < PF.size() ; i++){

            //if(PF.get(i).getDate().equals("2021-06-10")){
                axisData[i] = PF.get(i).getTime();
                yAxisData [i] = Integer.parseInt(PF.get(i).getStatusvalue());
                Log.d("D", "Data:" + axisData[i]);
                Log.d("D", "Data1:" + yAxisData [i]);
            //}
            String[] axisData = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept",
                "Oct", "Nov", "Dec"};
        int[] yAxisData = {50, 20, 15, 30, 20, 60, 15, 40, 45, 10, 90, 18};

           for(int i = s.length; i < (s.size() - 12); i--){
            int j = 0;
            axisValues.add(j, new AxisValue(j).setLabel(s.get(i).toString()));
            j++;
        }

        for (int i = in.size(); i < (in.size() - 12); i--){
            //int value =  yAxisData.get(i);
            yAxisValues.add(new PointValue(i, in.get(i)));
        }

        }*/

        //Log.d("D", "Data:" + c);


       /* List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();
        Line line = new Line(yAxisValues);


        for(int i = 0; i < s.length; i++){
            axisValues.add(i, new AxisValue(i).setLabel(s[i]));
        }

        for (int i = 0; i < in.length; i++){
            yAxisValues.add(new PointValue(i, in[i]));
        }

        List lines = new ArrayList();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        lineChartView.setLineChartData(data);

        Axis axis = new Axis();
        axis.setValues(axisValues);
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        data.setAxisYLeft(yAxis);

       // Line  line = new Line(yAxis).setColor(Color.parseColor("#9C27B0"));*/

    }


}
