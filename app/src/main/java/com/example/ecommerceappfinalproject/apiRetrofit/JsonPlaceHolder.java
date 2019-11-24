package com.example.ecommerceappfinalproject.apiRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolder {

     @GET("posts")
    Call<List<Post>> getPosts();
}
