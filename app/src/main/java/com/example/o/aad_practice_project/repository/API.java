package com.example.o.aad_practice_project.repository;

import com.example.o.aad_practice_project.model.Learner;
import com.example.o.aad_practice_project.model.SkillIqLearner;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    @GET("hours")
    Call<List<Learner>> getHours();

    @GET("skilliq")
    Call<List<SkillIqLearner>> getSkillIq();

}