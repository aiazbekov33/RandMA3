package com.example.randma3.data.network.apiservices;


import com.example.randma3.data.network.dtos.RickAndMortyResponse;
import com.example.randma3.data.network.dtos.location.Location;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LocationApiServices {
    @GET("/api/location/")
    Call<RickAndMortyResponse<Location>> fetchLocations();

    @GET("/api/location/{id}")
    Call<Location> fetchLocation(
            @Path("id") int id
    );
}
