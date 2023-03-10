package com.example.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat hhmm = new SimpleDateFormat("hh00");
        String x = "35";
        String y = "126";

        String date = yyyyMMdd.format(timestamp);
        String time = hhmm.format(timestamp);
        String Key = "test";
        String queryURL = MessageFormat.format("https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?serviceKey={0}&pageNo=1&numOfRows=100&dataType=XML&base_date={1}&base_time={2}&nx={3}&ny={4}",Key,date,time,x,y);
        System.out.println(queryURL);


    }
}