package com.capstone2.funiturear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CartActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
    }

    public void checkOut(View view) {
        //adapter.checkOutItem();
        //total.setText("$0.00");
        this.finish();
        startActivity(new Intent(CartActivity.this, MainActivity.class));
    }
}
