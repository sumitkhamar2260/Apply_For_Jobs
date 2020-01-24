package com.example.applyforjobs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class jobrow extends RecyclerView.Adapter<jobrow.MyViewHolder> {
    ArrayList<String> jobtitle;
    ArrayList<String> location;
    ArrayList<String> company,experience,salary,sector,description,jobid,companyid;
    Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView jobtit,loc,comp,exp,sal;
        CardView video_card;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            jobtit = (TextView)itemView.findViewById(R.id.job_title);
            loc = (TextView)itemView.findViewById(R.id.location);
            comp = (TextView)itemView.findViewById(R.id.company);
            exp=itemView.findViewById(R.id.experience);
            sal=itemView.findViewById(R.id.salary);
            video_card = (CardView)itemView.findViewById(R.id.video_card);
        }
    }
    public jobrow(Context context,ArrayList<String> jobtitle, ArrayList<String> company, ArrayList<String> location,ArrayList<String > experience,ArrayList<String > salary,ArrayList sector,ArrayList description,ArrayList jobid,ArrayList companyid){
        this.jobtitle = jobtitle;
        this.company = company;
        this.location = location;
        this.experience=experience;
        this.salary=salary;
        this.context = context;
        this.sector=sector;
        this.description=description;
        this.jobid=jobid;
        this.companyid=companyid;
    }
    public jobrow.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.jobs,parent,false);
        return new MyViewHolder(itemView);
    }
    public void onBindViewHolder(@NonNull jobrow.MyViewHolder holder, final int position) {
        String jobtit=(String) jobtitle.get(position);
        String com=(String) company.get(position);
        String loca=(String) location.get(position);
        String exp=(String) experience.get(position);
        String sal=(String) salary.get(position);
        holder.jobtit.setText(jobtit);
        holder.loc.setText(loca);
        holder.comp.setText(com);
        holder.exp.setText(exp);
        holder.sal.setText(sal);
        holder.video_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,applyjob.class);
                intent.putExtra("jobtitle",jobtitle.get(position));
                intent.putExtra("company",company.get(position));
                intent.putExtra("location",location.get(position));
                intent.putExtra("experience",experience.get(position));
                intent.putExtra("salary",salary.get(position));
                intent.putExtra("sector",sector.get(position));
                intent.putExtra("description",description.get(position));
                intent.putExtra("jobid",jobid.get(position));
                intent.putExtra("companyid",companyid.get(position));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }
    public int getItemCount() {
        return jobtitle.size();
    }

}
