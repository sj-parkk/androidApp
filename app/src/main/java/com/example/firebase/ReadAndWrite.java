package com.example.firebase;


import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.VO.User;
import com.example.myapplication.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReadAndWrite {
    DatabaseReference mDatabase;

    public void insertUser(User user) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("user").child(user.userName).setValue(user);
    }

    public void selectUser(User user){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        System.out.println(mDatabase.child("user").child(user.userName).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(!task.isSuccessful()){
                    Log.e("firebase","Error getting data",task.getException());
                }else{
                    Log.e("firebase",String.valueOf(task.getResult().getValue()));
                }
            }
        }));
    }

}
