package com.example.applyforjobs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class card_exp extends RecyclerView.Adapter<card_exp.newMyViewHolder> {
    ArrayList<String> jobtitle;
    ArrayList<String> company;
    Context context;
    public class newMyViewHolder extends RecyclerView.ViewHolder{
        TextView card_jobtitl,card_compan;
        CardView card_exp;
        public newMyViewHolder(@NonNull View itemView) {
            super(itemView);
            card_jobtitl = (TextView)itemView.findViewById(R.id.card_jobtitle);
            card_compan = (TextView)itemView.findViewById(R.id.card_company);
           // card_exp= (CardView)itemView.findViewById(R.id.cardview_exp);
        }
    }
    public card_exp(Context context,ArrayList<String> jobtitle, ArrayList<String> company){
        this.jobtitle = jobtitle;
        this.company = company;
        this.context = context;
        }
  @NonNull
    @Override
    public newMyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card_exp,parent,false);
        return new card_exp.newMyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(newMyViewHolder holder, int position) {
        String card_jobtit=(String) jobtitle.get(position);
        String card_com=(String) company.get(position);
        holder.card_jobtitl.setText(card_jobtit);
        holder.card_compan.setText(card_com);
        }

    public int getItemCount() {
        return jobtitle.size();
    }
}
