package com.example.randma3.data.local.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.randma3.data.network.dtos.character.Character;
import java.util.ArrayList;
import java.util.List;

@Dao
public interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert (ArrayList<Character> character);

    @Query("SELECT * FROM character")
    List<Character> getAll();

    @Update
    void update (Character character);

    @Delete
    void delete (Character character);

}
