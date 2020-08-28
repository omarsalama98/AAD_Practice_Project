package com.example.o.aad_practice_project.repository;

import com.example.o.aad_practice_project.model.Learner;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    @GET("api/hours")
    Call<List<Learner>> getHours();

    @GET("api/skilliq")
    Call<List<Learner>> getSkillIq();
}