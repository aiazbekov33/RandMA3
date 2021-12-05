package com.example.randma3.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.randma3.data.local.daos.CharacterDao;
import com.example.randma3.data.local.daos.EpisodeDao;
import com.example.randma3.data.local.daos.LocationDao;
import com.example.randma3.data.network.dtos.character.Character;
import com.example.randma3.data.network.dtos.episode.Episode;
import com.example.randma3.data.network.dtos.location.Location;

@Database(entities = {Character.class, Episode.class, Location.class}, version = 5)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CharacterDao characterDao();

    public abstract EpisodeDao episodeDao();

    public abstract LocationDao locationDao();
}
