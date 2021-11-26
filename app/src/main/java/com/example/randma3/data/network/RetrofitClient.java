package com.example.randma3.data.network;

import com.example.randma3.data.network.apiservices.CharacterApiServices;
import com.example.randma3.data.network.apiservices.EpisodeApiServices;
import com.example.randma3.data.network.apiservices.LocationApiServices;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private final OkHttpClient okHttpClient = new OkHttpClient()
            .newBuilder()
            .addInterceptor(provideLoggingInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS)
            .build();

    private HttpLoggingInterceptor provideLoggingInterceptor(){
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    }
    private final Retrofit provideRetrofit = new Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public CharacterApiServices provideCharacterApiService() {
        return provideRetrofit.create(CharacterApiServices.class);
    }

    public EpisodeApiServices provideEpisodeApiService() {
        return provideRetrofit.create(EpisodeApiServices.class);
    }
    public LocationApiServices provideLocationApiService(){
        return provideRetrofit.create(LocationApiServices.class);
    }
}
