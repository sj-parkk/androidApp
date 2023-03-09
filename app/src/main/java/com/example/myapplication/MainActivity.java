package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText idText;
    EditText pwText;
    AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idText=(EditText) findViewById(R.id.id_edit);
        pwText=(EditText) findViewById(R.id.pw_edit);

        Button buttonTwo = (Button)findViewById(R.id.button2);
        buttonTwo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(MainActivity.this, R.style.AlertDialogTheme));
                builder.setPositiveButton("ok",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogg,int which){
                        excute(true);
                    }
                })
                        .setNegativeButton("close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                excute(false);
                            }
                        });
                dialog = builder.create(); // dialog도 전역변수 선언함
                String x = idText.getText().toString();
                String y = pwText.getText().toString();
                dialog.setTitle("No Internet access");
                dialog.setMessage(x + " " + y);

                dialog.show();



            }
        });
    }
    public void excute(boolean result){
        if(result){
            Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
            startActivity(intent);
        }else{
            dialog.dismiss();
        }
    }
}