package com.capstone2.funiturear;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter_LifecycleAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    DatabaseReference mDataUsers;
    DatabaseReference mDataItems;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView txtUsername, txtEmail;
    String email;
    RecyclerView recyclerView;
    ArrayList<Items> list;
    MyAdapter myAdapter;
    EditText edsearch;
    Button btnsearch;
    private RecyclerView resultList;
    ArrayList<Items> arrayList;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList=new ArrayList<>();



        Intent intent = getIntent();
        email = intent.getStringExtra(LoginActivity.EMAIL);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.main_toolbar);




        resultList.setHasFixedSize(true);
        resultList.setLayoutManager(new LinearLayoutManager(this));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        list = new ArrayList<Items>();

        //-----------Get data from firebase to recyclerView-------------------\\
        mDataItems = FirebaseDatabase.getInstance().getReference().child("Item");
        mDataItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Items i = dataSnapshot1.getValue(Items.class);
                    list.add(i);
                }
                myAdapter = new MyAdapter(MainActivity.this, list);
                recyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //------------Get data from firebase to navigation header--------------\\
        View headerView = navigationView.getHeaderView(0);
        txtEmail = headerView.findViewById(R.id.txt_email);
        txtEmail.setText(email);
        txtUsername = headerView.findViewById(R.id.txt_username);

        mDataUsers = FirebaseDatabase.getInstance().getReference("Users");
        mDataUsers.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("name").addValueEventListener(new ValueEventListener() {
            String name;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                txtUsername.setText(dataSnapshot.getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Failed to read value.", error.toException());
            }
        });

        //-----------------ToolBar-----------------//
        setSupportActionBar(toolbar);
        //-----------------Navigation Drawer Menu-----------------//
        navigationView.bringToFront();
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        break;
                    case R.id.nav_catalogs:
                        startActivity(new Intent(MainActivity.this, CatalogsActivity.class));
                        break;
                    case R.id.nav_cart:
                        startActivity(new Intent(MainActivity.this, CartActivity.class));
                        break;
                    case R.id.nav_my_page:
                        startActivity(new Intent(MainActivity.this, MyPageActivity.class));
                        break;
                    case R.id.nav_correct_the_information:
                        startActivity(new Intent(MainActivity.this, CorrectTheInfoActivity.class));
                        break;
                    case R.id.nav_change_password:
                        startActivity(new Intent(MainActivity.this, ChangePasswordActivity.class));
                        break;
                    case R.id.nav_logout:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        break;
                }
                return true;
            }
        });

        //---------------------------------------------------------------
//        edsearch=findViewById(R.id.searchbar);
//        btnsearch=findViewById(R.id.search_button);
//        resultList=findViewById(R.id.recyclerView);

//
//        edsearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (!s.toString().isEmpty()) {
//                    search(s.toString());
//                }else{
//                    search("");
//                }
//
//            }
//        });


        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

//    private void search(String s) {
//        Query query= mDataItems.orderByChild("name").startAt(s).endAt(s+"\uf8ff");
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.hasChildren()){
//                    arrayList.clear();
//                    for(DataSnapshot dss:dataSnapshot.getChildren()){
//                        final Items items=dss.getValue(Items.class);
//                        arrayList.add(items);
//                    }
//
//                    MyAdapter myAdapter=new MyAdapter(getApplicationContext(),arrayList);
//                    recyclerView.setAdapter(myAdapter);
//                    myAdapter.notifyDataSetChanged();
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

