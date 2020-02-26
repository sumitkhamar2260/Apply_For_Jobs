package com.example.applyforjobs;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {
    @POST("/predict")
    Call<ResumeScoreModel> postData(@Body JsonObject body);
}
