package com.capstone2.funiturear;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CorrectTheInfoActivity extends AppCompatActivity {
    EditText UserName,  Gender, Phone;
    Button btnChange;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    DatabaseReference mDataUsers;
    private Context context;


    // private String Uid=mDataUsers.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).toString();
//    private String txtUserName=mDataUsers.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).toString();

    //    public void updateUser(String username,String phone,String gender){
//        username=UserName.getText().toString().trim();
//        phone=Phone.getText().toString().trim();
//        gender=Gender.getText().toString().trim();
//        mDataUsers.child("Users").child(Uid).child("name").setValue(username);
//        mDataUsers.child("Users").child(Uid).child("phone").setValue(phone);
//        mDataUsers.child("Users").child(Uid).child("gender").setValue(gender);
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct_the_info);

        UserName= findViewById(R.id.ed_Change_UserName);
        Gender= findViewById(R.id.ed_Change_gender);
        Phone= findViewById(R.id.ed_Change_Phone);
        btnChange=findViewById(R.id.btn_ChangeInfo);
//
        //      mFirebaseInstance=FirebaseDatabase.getInstance();
//        mFirebaseDatabase=mFirebaseInstance.getReference("Users");

        mDataUsers = FirebaseDatabase.getInstance().getReference("Users");

        mDataUsers.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            String name;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                UserName.setText(dataSnapshot.child("name").getValue().toString());
                Phone.setText(dataSnapshot.child("phone").getValue().toString());
                Gender.setText(dataSnapshot.child("gender").getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Failed to read value.", error.toException());
            }
        });

        btnChange.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String username,phone,gender;
                username=UserName.getText().toString().trim();
                phone=Phone.getText().toString().trim();
                gender=Gender.getText().toString().trim();
                mDataUsers.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("name").setValue(username);
                mDataUsers.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("phone").setValue(phone);
                mDataUsers.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("gender").setValue(gender);
                Toast.makeText(CorrectTheInfoActivity.this,"Info is Changed",Toast.LENGTH_SHORT).show();

            }
        });

    }



}
