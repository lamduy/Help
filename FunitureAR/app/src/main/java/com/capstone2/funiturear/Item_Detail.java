package com.capstone2.funiturear;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.StatsLog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Item_Detail extends AppCompatActivity {
    TextView items_title, items_price , item_description;
    ImageView items_image;
    String price, AR, title, name, description,model;
    String image;
    Button items_AR;
    FirebaseFirestore db;
    private FirebaseAnalytics mFirebaseAnalytics;
    DocumentReference itemRef;
    Items item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item__detail);


        items_image = findViewById(R.id.detail_image);
        items_title = findViewById(R.id.detail_item_title);
        items_price = findViewById(R.id.detail_item_price);
        items_AR = findViewById(R.id.detail_view_AR);
        item_description = findViewById(R.id.detail_item_description);

        //btnView=findViewById(R.id.view_AR);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        title = getIntent().getStringExtra("title");
        name = getIntent().getStringExtra("name");
        description = getIntent().getStringExtra("description");
        price = getIntent().getStringExtra("price");
        AR = getIntent().getStringExtra("isAR");
        image = getIntent().getStringExtra("image");
        model=getIntent().getStringExtra("Model");


        Picasso.get().load(image).into(items_image);
        items_title.setText(name);
        items_price.setText(price + " VNĐ");
        item_description.setText(description);


        if (AR.equals("true")) {
            items_AR.setVisibility(View.VISIBLE);
            //btnView.setVisibility(View.VISIBLE);
        }
        items_AR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Item_Detail.this,ViewAR.class);
                intent.putExtra("name",name);
                startActivity(intent);

            }
        });

    }


    public void viewInAR(View view) {
        Toast.makeText(Item_Detail.this,"aloalao",Toast.LENGTH_SHORT).show();

    }






}
