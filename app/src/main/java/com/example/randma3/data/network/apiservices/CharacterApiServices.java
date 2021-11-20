package com.example.randma3.data.network.apiservices;

import com.example.randma3.data.network.dtos.RickAndMortyResponse;
import com.example.randma3.data.network.dtos.character.Character;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CharacterApiServices {
    @GET("/api/character/")
    Call<RickAndMortyResponse<Character>> fetchCharacters();

    @GET("/api/character/{id}")
    Call<Character> fetchCharacter(
            @Path("id") int id
    );
}
