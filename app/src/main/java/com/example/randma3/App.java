package com.example.randma3;

import android.app.Application;

import com.example.randma3.data.local.AppDatabase;
import com.example.randma3.data.local.RoomClient;
import com.example.randma3.data.local.daos.CharacterDao;
import com.example.randma3.data.local.daos.EpisodeDao;
import com.example.randma3.data.local.daos.LocationDao;
import com.example.randma3.data.network.RetrofitClient;
import com.example.randma3.data.network.apiservices.CharacterApiServices;
import com.example.randma3.data.network.apiservices.EpisodeApiServices;
import com.example.randma3.data.network.apiservices.LocationApiServices;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class App extends Application {
//
//    public static CharacterDao characterDao;
//    public static EpisodeDao episodeDao;
//    public static LocationDao locationDao;
//    public static CharacterApiServices characterApiServices;
//    public static EpisodeApiServices episodeApiServices;
//    public static LocationApiServices locationApiServices;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        RoomClient roomClient = new RoomClient();
//        AppDatabase database = roomClient.provideDatabase(this);
//        characterDao = database.characterDao();
//        episodeDao = database.episodeDao();
//        locationDao = database.locationDao();
//
//        RetrofitClient retrofitClient = new RetrofitClient();
//        characterApiServices = retrofitClient.provideCharacterApiService();
//        episodeApiServices = retrofitClient.provideEpisodeApiService();
//        locationApiServices = retrofitClient.provideLocationApiService();
//    }
}
