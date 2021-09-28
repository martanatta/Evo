package com.example.evo.apiShmapi;

import com.example.evo.AudioListCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @GET("/categories/")
    Call<List<CategoryList>> getCategoryList(@Header("Authorization") String token);

    @GET("/favorite-audios/")
    Call<List<FavoriteAudioList>> getFavoriteAudioList();

    @GET("/categories/{id}/")
    Call<CategoryDetail> getCategoryDetail(@Path("id") int audioId);

    @POST("/auth/jwt/create/")
    Call<TokenRefresh> logIn(@Body TokenObtainPair tokenObtainPair);

    @GET("/audios/")
    Call<List<AudioListCategory>> getAllSongs();
}