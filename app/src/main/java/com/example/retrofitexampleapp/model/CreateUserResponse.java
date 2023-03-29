package com.example.retrofitexampleapp.model;

import com.google.gson.annotations.SerializedName;

public class CreateUserResponse {
    @SerializedName("name")
    public String name;
    @SerializedName("job")
    public String job;
    @SerializedName("id")
    public String id;
    @SerializedName("created_at")
    public String createdAt;
}
