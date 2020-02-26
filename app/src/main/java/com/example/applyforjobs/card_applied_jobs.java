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

public class card_applied_jobs extends RecyclerView.Adapter<card_applied_jobs.newMyViewHolder>  {
    ArrayList<String> appliedjobtitle;
    ArrayList<String> appliedcompany;
    ArrayList<String> appliedscore;
    Context context;

    public class newMyViewHolder extends RecyclerView.ViewHolder {
        TextView card_appliedjobtitl, card_appliedcompan, card_appliedscore;
        CardView card_appliedjobs;

        public newMyViewHolder(@NonNull View itemView) {
            super(itemView);
            card_appliedjobtitl = (TextView) itemView.findViewById(R.id.card_appliedjobtitle);
            card_appliedcompan = (TextView) itemView.findViewById(R.id.card_appliedcompany);
            card_appliedscore = (TextView) itemView.findViewById(R.id.card_appliedscore);
            card_appliedjobs = (CardView) itemView.findViewById(R.id.cardview_appliedjobs);
        }
    }
    public card_applied_jobs(Context context,ArrayList<String> appliedjobtitle, ArrayList<String> appliedcompany,ArrayList<String> appliedscore){
        this.appliedjobtitle = appliedjobtitle;
        this.appliedcompany = appliedcompany;
        this.appliedscore = appliedscore;
        this.context = context;
    }
    @NonNull
    @Override
    public card_applied_jobs.newMyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card_applied_jobs,parent,false);
        return new card_applied_jobs.newMyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(card_applied_jobs.newMyViewHolder holder, final int position) {
        String card_jobtit=(String) appliedjobtitle.get(position);
        String card_com=(String) appliedcompany.get(position);
        String card_score=(String) appliedscore.get(position);
        holder.card_appliedjobtitl.setText(card_jobtit);
        holder.card_appliedcompan.setText(card_com);
        holder.card_appliedscore.setText(card_score);


    }

    public int getItemCount() {
        return appliedjobtitle.size();
    }

}

