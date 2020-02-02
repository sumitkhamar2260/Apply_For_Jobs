package com.example.applyforjobs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Test extends AppCompatActivity {
    TextView testtype,q_no,question,option1,option2,option3,option4,completedTest,ts,testScore;
    Button next,applyForJob;
    FirebaseDatabase aptitudeQuestionsAnswers,logicalQuestionAnswers,verbalQuestionAnswers;
    ArrayList<String> aptitudeQuestions,aptitudeAnswers,aptitudeO1,aptitudeO2,aptitudeO3,aptitudeO4;
    ArrayList<String> logicalQuestions,logicalAnswers,logicalO1,logicalO2,logicalO3,logicalO4;
    ArrayList<String> verbalQuestions,verbalAnswers,verbalO1,verbalO2,verbalO3,verbalO4;
    ProgressDialog loading;
    int count=0,totalscore;
    MaterialCardView card1,card2,card3,card4,testResult;
    PieChart pieChart;
    int aptitudeScore=0,logicalScore=0,verbalScore=0;
    String answer=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Aptitude Test");
        setSupportActionBar(toolbar);
        intialization();
        retriveAptitudequesans();
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option1.setTypeface(Typeface.DEFAULT_BOLD);
                card1.setStrokeColor(Color.parseColor("#4b0082"));
                if(card2.isSelected() || card3.isSelected() || card4.isSelected()){
                    card2.setSelected(false);
                    card3.setSelected(false);
                    card4.setSelected(false);
                    option2.setTypeface(Typeface.DEFAULT);
                    card2.setStrokeColor(Color.parseColor("#D81B60"));
                    option4.setTypeface(Typeface.DEFAULT);
                    card4.setStrokeColor(Color.parseColor("#D81B60"));
                    option3.setTypeface(Typeface.DEFAULT);
                    card3.setStrokeColor(Color.parseColor("#D81B60"));
                    card1.setSelected(true);
                }else{
                    card1.setSelected(true);
                }
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option2.setTypeface(Typeface.DEFAULT_BOLD);
                card2.setStrokeColor(Color.parseColor("#4b0082"));
                if(card1.isSelected() || card3.isSelected() || card4.isSelected()){
                    card1.setSelected(false);
                    card3.setSelected(false);
                    card4.setSelected(false);
                    option1.setTypeface(Typeface.DEFAULT);
                    card1.setStrokeColor(Color.parseColor("#D81B60"));
                    option4.setTypeface(Typeface.DEFAULT);
                    card4.setStrokeColor(Color.parseColor("#D81B60"));
                    option3.setTypeface(Typeface.DEFAULT);
                    card3.setStrokeColor(Color.parseColor("#D81B60"));
                    card2.setSelected(true);
                }else{
                    card2.setSelected(true);
                }
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option3.setTypeface(Typeface.DEFAULT_BOLD);
                card3.setStrokeColor(Color.parseColor("#4b0082"));
                if(card1.isSelected() || card2.isSelected() || card4.isSelected()){
                    card2.setSelected(false);
                    card1.setSelected(false);
                    card4.setSelected(false);
                    option2.setTypeface(Typeface.DEFAULT);
                    card2.setStrokeColor(Color.parseColor("#D81B60"));
                    option4.setTypeface(Typeface.DEFAULT);
                    card4.setStrokeColor(Color.parseColor("#D81B60"));
                    option1.setTypeface(Typeface.DEFAULT);
                    card1.setStrokeColor(Color.parseColor("#D81B60"));
                    card3.setSelected(true);
                }else{
                    card3.setSelected(true);
                }
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option4.setTypeface(Typeface.DEFAULT_BOLD);
                card4.setStrokeColor(Color.parseColor("#4b0082"));
                if(card2.isSelected() || card3.isSelected() || card1.isSelected()){
                    card2.setSelected(false);
                    card3.setSelected(false);
                    card1.setSelected(false);
                    option2.setTypeface(Typeface.DEFAULT);
                    card2.setStrokeColor(Color.parseColor("#D81B60"));
                    option1.setTypeface(Typeface.DEFAULT);
                    card1.setStrokeColor(Color.parseColor("#D81B60"));
                    option3.setTypeface(Typeface.DEFAULT);
                    card3.setStrokeColor(Color.parseColor("#D81B60"));
                    card4.setSelected(true);
                }else{
                    card4.setSelected(true);
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>10 && count<20 ){
                    testtype.setText("Logical Reasoning");
                    toolbar.setTitle("Logical Reasoning");
                    if(card1.isSelected()){
                        if(option1.getText().equals(answer)){
                            logicalScore++;
                        }
                    }
                    if(card2.isSelected()){
                        if(option2.getText().equals(answer)){
                            logicalScore++;
                        }
                    }
                    if(card3.isSelected()){
                        if(option3.getText().equals(answer)){
                            logicalScore++;
                        }
                    }
                    if(card4.isSelected()){
                        if(option4.getText().equals(answer)){
                            logicalScore++;
                        }
                    }
                    setDefault();
                    randomNumberLogical();
                }else if(count ==20 ){
                    testtype.setText("Verbal Reasoning");
                    toolbar.setTitle("Verbal Reasoning");
                    if(card1.isSelected()){
                        if(option1.getText().equals(answer)){
                            logicalScore++;
                        }
                    }
                    if(card2.isSelected()){
                        if(option2.getText().equals(answer)){
                            logicalScore++;
                        }
                    }
                    if(card3.isSelected()){
                        if(option3.getText().equals(answer)){
                            logicalScore++;
                        }
                    }
                    if(card4.isSelected()){
                        if(option4.getText().equals(answer)){
                            logicalScore++;
                        }
                    }
                    setDefault();
                    retriveVerbalquesans();
                }else if(count>0 && count<10) {
                    toolbar.setTitle("Aptitude Test");
                    if(card1.isSelected()){
                        if(option1.getText().equals(answer)){
                            aptitudeScore++;
                        }
                    }
                    if(card2.isSelected()){
                        if(option2.getText().equals(answer)){
                            aptitudeScore++;
                        }
                    }
                    if(card3.isSelected()){
                        if(option3.getText().equals(answer)){
                            aptitudeScore++;
                        }
                    }
                    if(card4.isSelected()){
                        if(option4.getText().equals(answer)){
                            aptitudeScore++;
                        }
                    }
                    setDefault();
                    randomNumberAptitude();
                }else if(count==10){
                    toolbar.setTitle("Logical Reasoning");
                    testtype.setText("Logical Reasoning");
                    if(card1.isSelected()){
                        if(option1.getText().equals(answer)){
                            aptitudeScore++;
                        }
                    }
                    if(card2.isSelected()){
                        if(option2.getText().equals(answer)){
                            aptitudeScore++;
                        }
                    }
                    if(card3.isSelected()){
                        if(option3.getText().equals(answer)){
                            aptitudeScore++;
                        }
                    }
                    if(card4.isSelected()){
                        if(option4.getText().equals(answer)){
                            aptitudeScore++;
                        }
                    }
                    setDefault();
                    retriveLogicalquesans();
                }else if(count>20 && count<30){
                    testtype.setText("Verbal Reasoning");
                    toolbar.setTitle("Verbal Reasoning");
                    if(card1.isSelected()){
                        if(option1.getText().equals(answer)){
                            verbalScore++;
                        }
                    }
                    if(card2.isSelected()){
                        if(option2.getText().equals(answer)){
                            verbalScore++;
                        }
                    }
                    if(card3.isSelected()){
                        if(option3.getText().equals(answer)){
                            verbalScore++;
                        }
                    }
                    if(card4.isSelected()){
                        if(option4.getText().equals(answer)){
                            verbalScore++;
                        }
                    }
                    setDefault();
                    randomNumberVerbal();
                }else if(count == 30){
                    next.setText("Show Results");
                    if(card1.isSelected()){
                        if(option1.getText().equals(answer)){
                            verbalScore++;
                        }
                    }
                    if(card2.isSelected()){
                        if(option2.getText().equals(answer)){
                            verbalScore++;
                        }
                    }
                    if(card3.isSelected()){
                        if(option3.getText().equals(answer)){
                            verbalScore++;
                        }
                    }
                    if(card4.isSelected()){
                        if(option4.getText().equals(answer)){
                            verbalScore++;
                        }
                    }
                    setDefault();
                    card1.setClickable(false);
                    card2.setClickable(false);
                    card3.setClickable(false);
                    card4.setClickable(false);
                    showResults();
                    toolbar.setTitle("Test Results");
                }
                Log.e("Aptitude", String.valueOf(aptitudeScore));
                Log.e("Verbal", String.valueOf(verbalScore));
                Log.e("Logical", String.valueOf(logicalScore));
            }
        });
        applyForJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase fd=FirebaseDatabase.getInstance();
                DatabaseReference ref;
                FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                fd=FirebaseDatabase.getInstance();
                Intent intent=getIntent();
                String companyst=intent.getStringExtra("compid");
                String jobidst=intent.getStringExtra("jobid");
                ref=fd.getReference().child("Companies").child(companyst).child("Job Openings").child(jobidst);
                String uid=firebaseAuth.getCurrentUser().getUid();
                Map<Object,String> ob=new HashMap<>();
                ob.put("uid",uid);
                ob.put("Score",String.valueOf(totalscore));
                ref.child(uid).setValue(ob);
                startActivity(new Intent(Test.this,Homepage.class));
            }
        });
    }
    public void setDefault(){
        option1.setTypeface(Typeface.DEFAULT);
        card1.setStrokeColor(Color.parseColor("#D81B60"));
        card1.setSelected(false);
        option2.setTypeface(Typeface.DEFAULT);
        card2.setStrokeColor(Color.parseColor("#D81B60"));
        card2.setSelected(false);
        option3.setTypeface(Typeface.DEFAULT);
        card3.setStrokeColor(Color.parseColor("#D81B60"));
        card3.setSelected(false);
        option4.setTypeface(Typeface.DEFAULT);
        card4.setStrokeColor(Color.parseColor("#D81B60"));
        card4.setSelected(false);
    }
    public void intialization(){
        testtype=findViewById(R.id.test_type);
        q_no=findViewById(R.id.question_no);
        question=findViewById(R.id.question);
        option1=findViewById(R.id.option1);
        option2=findViewById(R.id.option2);
        option3=findViewById(R.id.option3);
        option4=findViewById(R.id.option4);
        next=findViewById(R.id.next_button);
        card1=findViewById(R.id.card1);
        card2=findViewById(R.id.card2);
        card3=findViewById(R.id.card3);
        card4=findViewById(R.id.card4);
        completedTest=findViewById(R.id.textView);
        ts=findViewById(R.id.textView2);
        testScore=findViewById(R.id.score);
        testResult=findViewById(R.id.test_result);
        pieChart=findViewById(R.id.piechart);
        applyForJob=findViewById(R.id.applyforjob);
        loading = new ProgressDialog(this);
        loading.setMessage("Loading...");
        loading.show();
        setGraph();
        aptitudeQuestionsAnswers=FirebaseDatabase.getInstance();
        logicalQuestionAnswers=FirebaseDatabase.getInstance();
        verbalQuestionAnswers=FirebaseDatabase.getInstance();
        aptitudeAnswers=new ArrayList<>();
        logicalAnswers=new ArrayList<>();
        verbalAnswers=new ArrayList<>();
        aptitudeQuestions=new ArrayList<>();
        logicalQuestions=new ArrayList<>();
        verbalQuestions=new ArrayList<>();
        aptitudeO1=new ArrayList<>();
        logicalO1=new ArrayList<>();
        verbalO1=new ArrayList<>();
        aptitudeO2=new ArrayList<>();
        logicalO2=new ArrayList<>();
        verbalO2=new ArrayList<>();
        aptitudeO3=new ArrayList<>();
        logicalO3=new ArrayList<>();
        verbalO3=new ArrayList<>();
        aptitudeO4=new ArrayList<>();
        logicalO4=new ArrayList<>();
        verbalO4=new ArrayList<>();

    }
    public void retriveAptitudequesans(){
        aptitudeQuestionsAnswers.getReference().child("test").child("aptitude").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot shot:dataSnapshot.getChildren()) {
                    String q = shot.child("question").getValue(String.class);
                    aptitudeQuestions.add(q);
                }
                for(DataSnapshot shot:dataSnapshot.getChildren()) {
                    String a = shot.child("answer").getValue(String.class);
                    aptitudeAnswers.add(a);
                }
                for(DataSnapshot shot:dataSnapshot.getChildren()) {
                    String O1 = shot.child("option1").getValue(String.class);
                    aptitudeO1.add(O1);
                }
                for(DataSnapshot shot:dataSnapshot.getChildren()) {
                    String O2 = shot.child("option2").getValue(String.class);
                    aptitudeO2.add(O2);
                }
                for(DataSnapshot shot:dataSnapshot.getChildren()) {
                    String O3 = shot.child("option3").getValue(String.class);
                    aptitudeO3.add(O3);
                }
                for(DataSnapshot shot:dataSnapshot.getChildren()) {
                    String O4 = shot.child("option4").getValue(String.class);
                    aptitudeO4.add(O4);
                }
                randomNumberAptitude();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void retriveLogicalquesans(){
        logicalQuestionAnswers.getReference().child("test").child("logical reasoning").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot shot:dataSnapshot.getChildren()) {
                    String q = shot.child("question").getValue(String.class);
                    logicalQuestions.add(q);
                }
                for(DataSnapshot shot:dataSnapshot.getChildren()) {
                    String a = shot.child("answer").getValue(String.class);
                    logicalAnswers.add(a);
                }
                for(DataSnapshot shot:dataSnapshot.getChildren()) {
                    String O1 = shot.child("option1").getValue(String.class);
                    logicalO1.add(O1);
                }
                for(DataSnapshot shot:dataSnapshot.getChildren()) {
                    String O2 = shot.child("option2").getValue(String.class);
                    logicalO2.add(O2);
                }
                for(DataSnapshot shot:dataSnapshot.getChildren()) {
                    String O3 = shot.child("option3").getValue(String.class);
                    logicalO3.add(O3);
                }
                for(DataSnapshot shot:dataSnapshot.getChildren()) {
                    String O4 = shot.child("option4").getValue(String.class);
                    logicalO4.add(O4);
                }
                randomNumberLogical();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void retriveVerbalquesans(){
        verbalQuestionAnswers.getReference().child("test").child("verbal reasoning").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot shot:dataSnapshot.getChildren()) {
                    String q = shot.child("question").getValue(String.class);
                    verbalQuestions.add(q);
                }
                for(DataSnapshot shot:dataSnapshot.getChildren()) {
                    String a = shot.child("answer").getValue(String.class);
                    verbalAnswers.add(a);
                }
                for(DataSnapshot shot:dataSnapshot.getChildren()) {
                    String O1 = shot.child("option1").getValue(String.class);
                    verbalO1.add(O1);
                }
                for(DataSnapshot shot:dataSnapshot.getChildren()) {
                    String O2 = shot.child("option2").getValue(String.class);
                    verbalO2.add(O2);
                }
                for(DataSnapshot shot:dataSnapshot.getChildren()) {
                    String O3 = shot.child("option3").getValue(String.class);
                    verbalO3.add(O3);
                }
                for(DataSnapshot shot:dataSnapshot.getChildren()) {
                    String O4 = shot.child("option4").getValue(String.class);
                    verbalO4.add(O4);
                }
                randomNumberVerbal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void randomNumberAptitude(){
        Random rand = new Random();
        int n = rand.nextInt(aptitudeQuestions.size());
        showAptitudeQuestions(n);
        answer=checkAptitudeAnswer(n);
    }
    public void showAptitudeQuestions(int n){
        int qno=count+1;
        q_no.setText(qno+".");
        question.setText(aptitudeQuestions.get(n));
        option1.setText(aptitudeO1.get(n));
        option2.setText(aptitudeO2.get(n));
        option3.setText(aptitudeO3.get(n));
        option4.setText(aptitudeO4.get(n));
        if(loading.isShowing()) {
            loading.dismiss();
            intialVisiblity();
        }
        aptitudeQuestions.remove(n);
        aptitudeO1.remove(n);
        aptitudeO2.remove(n);
        aptitudeO3.remove(n);
        aptitudeO4.remove(n);

    }
    public String checkAptitudeAnswer(int n){
        String answer=aptitudeAnswers.get(n);
        aptitudeAnswers.remove(n);
        count++;
        return answer;
    }
    public void randomNumberLogical(){
        Random rand = new Random();
        int n = rand.nextInt(logicalQuestions.size());
        showLogicalQuestions(n);
        answer=checkLogicalAnswer(n);
    }
    public void showLogicalQuestions(int n){
        int qno=count+1;
        q_no.setText(qno+".");
        question.setText(logicalQuestions.get(n));
        option1.setText(logicalO1.get(n));
        option2.setText(logicalO2.get(n));
        option3.setText(logicalO3.get(n));
        option4.setText(logicalO4.get(n));
        logicalQuestions.remove(n);
        logicalO1.remove(n);
        logicalO2.remove(n);
        logicalO3.remove(n);
        logicalO4.remove(n);
    }
    public String checkLogicalAnswer(int n){
        String answer=logicalAnswers.get(n);
        logicalAnswers.remove(n);
        count++;
        return answer;
    }
    public void randomNumberVerbal(){
        Random rand = new Random();
        int n = rand.nextInt(verbalQuestions.size());
        showVerbalQuestions(n);
        answer=checkVerbalAnswer(n);
    }
    public void showVerbalQuestions(int n){
        int qno=count+1;
        q_no.setText(qno+".");
        question.setText(verbalQuestions.get(n));
        option1.setText(verbalO1.get(n));
        option2.setText(verbalO2.get(n));
        option3.setText(verbalO3.get(n));
        option4.setText(verbalO4.get(n));
        verbalQuestions.remove(n);
        verbalO1.remove(n);
        verbalO2.remove(n);
        verbalO3.remove(n);
        verbalO4.remove(n);
    }
    public String checkVerbalAnswer(int n){
        String answer=verbalAnswers.get(n);
        verbalAnswers.remove(n);
        count++;
        return answer;
    }
    public void setGraph(){
        pieChart.getDescription().setEnabled(false);
        pieChart.setEntryLabelTextSize(16.0f);
        pieChart.setCenterTextTypeface(Typeface.SERIF);
        pieChart.setEntryLabelColor(Color.parseColor("#000000"));
        pieChart.setDrawSlicesUnderHole(true);
        pieChart.setDrawHoleEnabled(false);
        pieChart.invalidate();
        Legend l=pieChart.getLegend();
        l.setFormSize(10f);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setTextSize(12f);
        l.setTypeface(Typeface.SANS_SERIF);
    }
    public void visiblity(){
        testtype.setVisibility(View.GONE);
        q_no.setVisibility(View.GONE);
        question.setVisibility(View.GONE);
        card1.setVisibility(View.GONE);
        card2.setVisibility(View.GONE);
        card3.setVisibility(View.GONE);
        card4.setVisibility(View.GONE);
        next.setVisibility(View.GONE);
        completedTest.setVisibility(View.VISIBLE);
        testResult.setVisibility(View.VISIBLE);
        applyForJob.setVisibility(View.VISIBLE);
    }
    public void showResults(){
        List<PieEntry> pieChartEntries = new ArrayList<>();
        pieChartEntries.add(new PieEntry(aptitudeScore, "Aptitude"));
        pieChartEntries.add(new PieEntry(logicalScore, "Logical Reasoning"));
        pieChartEntries.add(new PieEntry(verbalScore, "Verbal Reasoning"));
        PieDataSet set = new PieDataSet(pieChartEntries, "");
        PieData data = new PieData(set);
        pieChart.setData(data);
        data.setValueTextSize(16f);
        set.setSliceSpace(1.0f);
        set.setColors(ColorTemplate.MATERIAL_COLORS);
        testScore.setText(aptitudeScore+logicalScore+verbalScore + "/30");
        totalscore = aptitudeScore+logicalScore+verbalScore;
        visiblity();
    }
    public void intialVisiblity(){
        testtype.setVisibility(View.VISIBLE);
        q_no.setVisibility(View.VISIBLE);
        question.setVisibility(View.VISIBLE);
        card1.setVisibility(View.VISIBLE);
        card2.setVisibility(View.VISIBLE);
        card3.setVisibility(View.VISIBLE);
        card4.setVisibility(View.VISIBLE);
        next.setVisibility(View.VISIBLE);
    }
}
