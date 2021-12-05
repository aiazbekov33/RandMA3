package com.example.randma3.data.di;
import android.content.Context;

import com.example.randma3.data.local.AppDatabase;
import com.example.randma3.data.local.RoomClient;
import com.example.randma3.data.local.daos.CharacterDao;
import com.example.randma3.data.local.daos.EpisodeDao;
import com.example.randma3.data.local.daos.LocationDao;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class LocalModule {

    @Singleton
    private final RoomClient roomClient = new RoomClient();

    @Provides
    @Singleton
    AppDatabase provideDataBase(@ApplicationContext Context context){
        return roomClient.provideDatabase(context);
    }

    @Provides
    @Singleton
    CharacterDao provideCharacterDao (AppDatabase database){
        return roomClient.provideCharacterDao(database);
    }

    @Provides
    @Singleton
    EpisodeDao provideEpisodeDao (AppDatabase database){
        return roomClient.provideEpisodeDao(database);
    }

    @Provides
    @Singleton
    LocationDao provideLocationDao (AppDatabase database){
        return roomClient.provideLocationDao(database);
    }
}
