package com.example.randma3.data.local.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.randma3.data.network.dtos.location.Location;
import java.util.ArrayList;
import java.util.List;

@Dao
public interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert (ArrayList<Location> locations);

    @Query("SELECT * FROM location")
    List<Location> getAll();

    @Update
    void update (Location location);

    @Delete
    void delete (Location location);



}
