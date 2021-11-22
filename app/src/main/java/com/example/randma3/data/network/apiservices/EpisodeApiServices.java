package com.example.randma3.data.network.apiservices;
import com.example.randma3.data.network.dtos.RickAndMortyResponse;
import com.example.randma3.data.network.dtos.episode.Episode;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EpisodeApiServices {
    @GET("/api/location/")
    Call<RickAndMortyResponse<Episode>> fetchEpisodes();
    @GET("/api/location/{id}")
    Call<Episode> fetchEpisode(
            @Path("id") int id
    );
}
