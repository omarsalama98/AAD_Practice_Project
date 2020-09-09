package com.example.o.aad_practice_project.repository;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This is a Singleton wrapper class for {@link Retrofit} and all network related functions
 */
public class RetrofitClient {

    public static final String BASE_URL = "https://gadsapi.herokuapp.com/api/";

    // volatile keyword ensures that all threads use the same object that is in main memory
    private static volatile RetrofitClient mRetrofitClient = null;
    private Retrofit.Builder retrofitBuilder;
    private OkHttpClient.Builder httpClientBuilder;
    private static HttpLoggingInterceptor logger = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    /**
     * private constructor for the Singleton design pattern
     */
    private RetrofitClient() {
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(logger);

        retrofitBuilder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build());

    }


    /**
     * @return Singleton instance of {@link RetrofitClient}
     */
    public static RetrofitClient getInstance() {
        if (mRetrofitClient == null) {
            synchronized (RetrofitClient.class) {
                // check again to avoid multi-thread access
                if (mRetrofitClient == null) {
                    mRetrofitClient = new RetrofitClient();
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
