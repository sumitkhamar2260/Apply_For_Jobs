package com.example.applyforjobs;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class skilladapter extends RecyclerView.Adapter<skilladapter.MyViewHolder> {

    ArrayList<String> skills;
    Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView skilltv;
        CardView skillcard;
        public MyViewHolder(View itemview)
        {
            super(itemview);
            skilltv=(TextView) itemview.findViewById(R.id.skill_reo);
            skillcard=(CardView) itemview.findViewById(R.id.skill_card);
        }
    }
    public skilladapter(Context context,ArrayList<String> skills){
        this.context=context;
        this.skills=skills;
    }
    public skilladapter.MyViewHolder onCreateViewHolder(ViewGroup parent,int viewtype){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.skillcard,parent,false);
        return new MyViewHolder(itemView);
    }
    public void onBindViewHolder(@NonNull skilladapter.MyViewHolder holder, final int position) {
        String skill=skills.get(position).toString();
        holder.skilltv.setText(skill);

    }
    public int getItemCount() {
        return skills.size();
    }

}
