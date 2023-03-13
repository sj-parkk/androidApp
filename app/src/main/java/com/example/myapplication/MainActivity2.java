package com.example.myapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.etc.OpenAPI;
import com.example.etc.PermissionSupport;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

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
    String Key = "TsU%2FUnhzWz0KbjCKUT3ywEj%2Fy3lgKEvXphf1lKX5NJ88HgLjIgt5DJQLwfOcuqAVgDyLe4J0ctk555uxw0HEjg%3D%3D";
    String data = "";
    private FusedLocationProviderClient fusedLocationClient;

    TextView textView;
    int longitude;
    int latitude;

    private PermissionSupport permission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button button1 = (Button) findViewById(R.id.button_main2);
        textView = (TextView) findViewById(R.id.textView_main2);

        permissionCheck();


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        //권한 요청 확인
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);


        System.out.println(location.getLongitude());
        System.out.println(location.getLatitude());



        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    latitude = (int) location.getLatitude();//x축
                    longitude = (int) location.getLongitude();//y축
                    System.out.println("x 축입니다 : " + latitude);
                    System.out.println("y 축입니다 : " + longitude);
                }
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //위치 정보 받아오고
                // 날짜랑 시간 받아온 다음에 시간은 한시간 빼는 걸로

                String x = String.valueOf(latitude);
                String y = String.valueOf(longitude);
                String[] dateArray = dateReturn();
                String date = dateArray[0];
                String time = dateArray[1];
                String queryURL = MessageFormat.format("https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?serviceKey={0}&pageNo=1&numOfRows=100&dataType=XML&base_date={1}&base_time={2}&nx={3}&ny={4}", Key, date, "0500", x, y);
                System.out.println(queryURL);
                OpenAPI openAPI = new OpenAPI(queryURL);
                openAPI.execute();
            }
        });

    }

    private String[] dateReturn() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat hhmm = new SimpleDateFormat("hh00");
        String[] x = {yyyyMMdd.format(timestamp), hhmm.format(timestamp)};
        return x;
    }

    private void permissionCheck() {
        permission = new PermissionSupport(this, this);
        if (!permission.checkPermission()) {
            permission.requestPermission();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //여기서도 리턴이 false로 들어온다면 (사용자가 권한 허용 거부)
        if (!permission.permissionResult(requestCode, permissions, grantResults)) {
            // 다시 permission 요청
            permission.requestPermission();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}