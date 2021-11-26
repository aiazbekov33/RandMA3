package com.example.randma3.data.network.dtos.location;

import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private  String name;

    @SerializedName("type")
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
