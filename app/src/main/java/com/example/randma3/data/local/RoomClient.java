package com.example.randma3.data.local;

import android.content.Context;

import androidx.room.Room;

import com.example.randma3.data.local.daos.CharacterDao;
import com.example.randma3.data.local.daos.EpisodeDao;
import com.example.randma3.data.local.daos.LocationDao;

public class RoomClient {

    public AppDatabase provideDatabase(Context context){
        return Room.databaseBuilder(context,AppDatabase.class, "rick_and_morty_db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }
    public CharacterDao provideCharacterDao(AppDatabase database){
        return database.characterDao();
    }
    public EpisodeDao provideEpisodeDao(AppDatabase database){
        return database.episodeDao();
    }
    public LocationDao provideLocationDao(AppDatabase database){
        return database.locationDao();
    }

}
