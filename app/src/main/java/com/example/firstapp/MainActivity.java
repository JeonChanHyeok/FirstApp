package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public static int i=0; // 1001 출발지
    public static double C_a = 0;
    public static double C_b = 0;
    public static double D_a = 0;
    public static double D_b = 0;
    URL url = null;
    HttpURLConnection urlConnection = null;
    BufferedInputStream buf = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView startText = (TextView) findViewById(R.id.startText);
        TextView endText = (TextView) findViewById(R.id.endText);
        if(i!=0) {
            Intent intent2 = getIntent();
            String tr = intent2.getExtras().getString("출도");
            double a = intent2.getExtras().getDouble("위도");
            double b = intent2.getExtras().getDouble("경도");
            if(tr.equals("c")) {
                C_a = a;
                C_b = b;
                if(D_a == 0 && D_b == 0){
                    startText.setText(C_a + "/" + C_b);
                }else{
                    startText.setText(C_a + "/" + C_b);
                    endText.setText(D_a + "/" + D_b);
                }
            }else if(tr.equals("d")){
                D_a = a;
                D_b = b;
                if(C_a == 0 && C_b == 0){
                    endText.setText(D_a + "/" + D_b);
                }else{
                    endText.setText(D_a + "/" + D_b);
                    startText.setText(C_a + "/" + C_b);
                }
            }
        }
        Button start_button = (Button) findViewById(R.id.start);
        Button end_button = (Button) findViewById(R.id.end);
        Button button = (Button) findViewById(R.id.button);
        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Start.class);
                startActivity(intent);
                i = i + 1;
                finish();
            }
        });
        end_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), End.class);
                startActivity(intent);
                i = i + 1;
                finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    url= new URL("https://maps.googleapis.com/maps/api/directions/json?origin="+C_a+","+C_b+"&destination="+D_a+","+D_b+"&mode=transit&departure_time=now&key=AIzaSyDAqyetKaBKL9WWKwVOeBROyBAxGEUwFjI");
                    InputStreamReader ips = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");



                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }
}