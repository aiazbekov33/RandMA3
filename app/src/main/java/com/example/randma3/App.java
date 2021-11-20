package com.example.randma3;

import android.app.Application;

import com.example.randma3.data.network.RetrofitClient;
import com.example.randma3.data.network.apiservices.CharacterApiServices;

public class App extends Application {

    public static CharacterApiServices characterApiServices;

    @Override
    public void onCreate() {
        super.onCreate();

        RetrofitClient retrofitClient = new RetrofitClient();
        characterApiServices = retrofitClient.provideCharacterApiService();
    }
}
