package com.example.randma3.data.local.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.randma3.data.network.dtos.episode.Episode;
import java.util.ArrayList;
import java.util.List;

@Dao
public interface EpisodeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert (ArrayList<Episode> episode);

    @Query("SELECT * FROM episode")
    List<Episode> getAll();

    @Update
    void update (Episode episode);

    @Delete
    void delete (Episode episode);
}
