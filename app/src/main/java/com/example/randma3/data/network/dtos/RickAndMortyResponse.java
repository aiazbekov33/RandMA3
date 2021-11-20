package com.example.randma3.data.network.dtos;

import com.example.randma3.data.network.dtos.character.Character;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RickAndMortyResponse<T> extends Character {
    @SerializedName("info")
    private Info info;

    @SerializedName("results")
    private ArrayList<T> results;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public ArrayList<Character> getResults() {
        return (ArrayList<Character>) results;
    }

    public void setResults(ArrayList<T> results) {
        this.results = results;
    }
}
