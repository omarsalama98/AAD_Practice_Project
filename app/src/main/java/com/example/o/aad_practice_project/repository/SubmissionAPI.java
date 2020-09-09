package com.example.o.aad_practice_project.repository;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface SubmissionAPI {

    @POST
    @FormUrlEncoded
    Call<Void> submit(
            @Url String submissionUrl,
            @Field("entry.1824927963") String emailAddress,
            @Field("entry.1877115667") String firstName,
            @Field("entry.2006916086") String lastName,
            @Field("entry.284483984") String projectLink
    );
}
