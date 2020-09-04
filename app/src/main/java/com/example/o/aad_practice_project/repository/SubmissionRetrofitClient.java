package com.example.o.aad_practice_project.repository;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmissionRetrofitClient {

    public static final String BASE_URL = "https://docs.google.com/forms/d/e/";

    // volatile keyword ensures that all threads use the same object that is in main memory
    private static volatile SubmissionRetrofitClient mRetrofitClient = null;
    private Retrofit.Builder retrofitBuilder;

    /**
     * private constructor for the Singleton design pattern
     */
    private SubmissionRetrofitClient() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        retrofitBuilder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build());

    }


    /**
     * @return Singleton instance of {@link RetrofitClient}
     */
    public static SubmissionRetrofitClient getInstance() {
        if (mRetrofitClient == null) {
            synchronized (SubmissionRetrofitClient.class) {
                // check again to avoid multi-thread access
                if (mRetrofitClient == null) {
                    mRetrofitClient = new SubmissionRetrofitClient();
                }
            }
        }
        return mRetrofitClient;
    }


    /**
     * Create an implementation of the API endpoints defined by the {@code API} interface
     *
     * @param API The interface to create its implementation
     * @return The implemented class
     */
    public <T> T getAPI(final Class<T> API) {
        return retrofitBuilder.build().create(API);
    }


}
