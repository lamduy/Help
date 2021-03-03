package com.capstone2.funiturear;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Items> items;

    public MyAdapter(Context c, ArrayList<Items> listItem) {
        context = c;
        items = listItem;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.items_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final String urlPrice = items.get(position).getPrice();
        final String urlTitle = items.get(position).getTitle();
        final  String urlName = items.get(position).getName();
        final String urlImage = items.get(position).getImage();
        final  String urlDescription = items.get(position).getDescription();
        final  String check = items.get(position).getisAR();
        final String model=items.get(position).getModel();
        Picasso.get().load(urlImage).into(holder.items_image);
        holder.items_title.setText(urlName);
        holder.items_price.setText(urlPrice + " VNĐ");
        if (check.equals("true")) {
            holder.items_AR.setVisibility(View.VISIBLE);
        }
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Item_Detail.class);
                intent.putExtra("image", urlImage);
                intent.putExtra("description", urlDescription);
                intent.putExtra("title", urlTitle);
                intent.putExtra("name", urlName);
                intent.putExtra("price", urlPrice);
                intent.putExtra("isAR", check);
                intent.putExtra("Model",model);
                context.startActivity(intent);
            }
        });
        holder.items_AR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten=new Intent(context,ViewAR.class);
                context.startActivity(inten);
            }
        });

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView items_title, items_price,items_AR;

        ImageView items_image;
        LinearLayout linearLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            items_image = itemView.findViewById(R.id.item_view_image);
            items_AR = itemView.findViewById(R.id.tv_item_is_AR);
            items_title = itemView.findViewById(R.id.item_view_title);
            items_price = itemView.findViewById(R.id.item_view_price);
            linearLayout = itemView.findViewById(R.id.item_linearLayout);
        }



    }

}
