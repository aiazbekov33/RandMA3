package com.example.randma3.data.di;

import com.example.randma3.data.network.RetrofitClient;
import com.example.randma3.data.network.apiservices.CharacterApiServices;
import com.example.randma3.data.network.apiservices.EpisodeApiServices;
import com.example.randma3.data.network.apiservices.LocationApiServices;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Singleton
    private final RetrofitClient retrofitClient = new RetrofitClient();

    @Provides
    @Singleton
    CharacterApiServices provideApiService(){
        return new RetrofitClient().provideCharacterApiService();
    }

    @Provides
    @Singleton
    EpisodeApiServices provideEpisodeApiService(){
        return new RetrofitClient().provideEpisodeApiService();
    }

    @Provides
    @Singleton
    LocationApiServices provideLocationApiService(){
        return new RetrofitClient().provideLocationApiService();
    }
}
