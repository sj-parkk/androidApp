package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.etc.OpenAPI;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;

public class MainActivity2 extends AppCompatActivity {
    String Key= "TsU%2FUnhzWz0KbjCKUT3ywEj%2Fy3lgKEvXphf1lKX5NJ88HgLjIgt5DJQLwfOcuqAVgDyLe4J0ctk555uxw0HEjg%3D%3D";
    String data= "";

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button button1 = (Button) findViewById(R.id.button_main2);
        textView = (TextView) findViewById(R.id.textView_main2);

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //위치 정보 받아오고
                // 날짜랑 시간 받아온 다음에 시간은 한시간 빼는 걸로

                String x = "35";
                String y = "126";
                String[] dateArray = dateReturn();
                String date = dateArray[0];
                String time = dateArray[1];
                String queryURL = MessageFormat.format("https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?serviceKey={0}&pageNo=1&numOfRows=100&dataType=XML&base_date={1}&base_time={2}&nx={3}&ny={4}",Key,date,time,x,y);
                System.out.println(queryURL);
                OpenAPI openAPI = new OpenAPI(queryURL);
                openAPI.execute();
            }
        });

    }

    private String[] dateReturn(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat hhmm = new SimpleDateFormat("hh00");
        String[] x = {yyyyMMdd.format(timestamp),hhmm.format(timestamp)};
        return x;
    }

}