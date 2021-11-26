package com.example.randma3.data.network.dtos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RickAndMortyResponse<T> {
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

    public ArrayList<T> getResults() {
        return (ArrayList<T>) results;
    }

    public void setResults(ArrayList<T> results) {
        this.results = results;
    }
}
