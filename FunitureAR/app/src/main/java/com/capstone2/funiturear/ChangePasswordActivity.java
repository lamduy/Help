package com.capstone2.funiturear;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText newPass,currentPass,confirmPass;
    Button btnChangePass;
    DatabaseReference mDataUsers;
    String uid= FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        currentPass=findViewById(R.id.ed_current_password);
        newPass=findViewById(R.id.ed_new_password);
        confirmPass=findViewById(R.id.ed_confirm_password);

        btnChangePass=findViewById(R.id.btn_ChangePass);


        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change();
               // mDataUsers = FirebaseDatabase.getInstance().getReference("Users").child().child("pass").setValue(newPass);
                //mDataUsers.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("pass").setValue(newPass);

            }
        });
    }
    public void change(){
        final String oldPasss,newPasss,confirmPasss;
        oldPasss=currentPass.getText().toString();
        newPasss=newPass.getText().toString();
        confirmPasss=confirmPass.getText().toString();

        if(oldPasss.equals("")){
            Toast.makeText(ChangePasswordActivity.this,"Current Passwords is required. ",Toast.LENGTH_SHORT).show();
        }else if(newPasss.equals("")){
            Toast.makeText(ChangePasswordActivity.this,"New Passwords is required. ",Toast.LENGTH_SHORT).show();
        }else if(newPasss.length()<6 && oldPasss.length()<6 ){
            Toast.makeText(ChangePasswordActivity.this," Passwords too short. ",Toast.LENGTH_SHORT).show();
        }else if(newPasss.equals(oldPasss)){
            Toast.makeText(ChangePasswordActivity.this,"New Passwords is same Old Passwords. ",Toast.LENGTH_SHORT).show();
        }
        else if(!confirmPasss.equals(newPasss)){
            Toast.makeText(ChangePasswordActivity.this,"Confirm Passwords is not  same New Passwords. ",Toast.LENGTH_SHORT).show();
        }else {
            final FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
            final String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
            AuthCredential credential= EmailAuthProvider.getCredential(user.getEmail(),oldPasss);

            user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        user.updatePassword(newPasss);
                        Toast.makeText(ChangePasswordActivity.this,"Passwords is Changed",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ChangePasswordActivity.this,"Passwords change Failed",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}
