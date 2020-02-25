package com.example.applyforjobs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class card_edu extends RecyclerView.Adapter<card_edu.neMyViewHolder> {
    ArrayList<String> degree;
    ArrayList<String> fos;
    Context context;

    public class neMyViewHolder extends RecyclerView.ViewHolder{
        TextView card_degree,card_fos;
        CardView card_edu;
        public neMyViewHolder(@NonNull View itemView) {
            super(itemView);
            card_degree = (TextView)itemView.findViewById(R.id.card_degree);
            card_fos = (TextView)itemView.findViewById(R.id.card_fos);
            card_edu= (CardView)itemView.findViewById(R.id.cardview_edu);
        }}
        public card_edu(Context context,ArrayList<String> degree, ArrayList<String> fos){

            this.degree = degree;
            this.fos = fos;
            this.context = context;
        }
    @NonNull
    @Override
    public card_edu.neMyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card_edu,parent,false);
        return new card_edu.neMyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(card_edu.neMyViewHolder holder, final int position) {
        String card_deg = (String) degree.get(position);
        String card_fieldos = (String) fos.get(position);
        holder.card_degree.setText(card_deg);
        holder.card_fos.setText(card_fieldos);
        holder.card_edu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, person_education.class);
                intent.putExtra("degree", degree.get(position));
                intent.putExtra("field of study", fos.get(position));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    public int getItemCount() {
        return degree.size();
    }


}
