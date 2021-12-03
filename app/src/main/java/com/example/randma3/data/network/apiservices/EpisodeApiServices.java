package com.example.randma3.data.network.apiservices;
import com.example.randma3.data.network.dtos.RickAndMortyResponse;
import com.example.randma3.data.network.dtos.episode.Episode;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EpisodeApiServices {
    @GET("/api/episode/")
    Call<RickAndMortyResponse<Episode>> fetchEpisodes(
            @Query("page") int page);

    @GET("/api/episode/{id}")
    Call<Episode> fetchEpisode(
            @Path("id") int id
    );
}
