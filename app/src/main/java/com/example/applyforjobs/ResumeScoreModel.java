package com.example.applyforjobs;

import com.google.gson.annotations.SerializedName;

public class ResumeScoreModel {
    @SerializedName("prediction")
    String ResumeScore;

    public String getResumeScore() {
        return ResumeScore;
    }

    public void setResumeScore(String resumeScore) {
        ResumeScore = resumeScore;
    }
}
