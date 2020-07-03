package com.example.geektechyoutubeparcer.network

import com.example.geektechyoutubeparcer.model.Playlist
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeAPi {

    @GET("api/v1/notes/")
    fun fetchAllPlaylists(
        @Query("body") body: String,
        @Query("title") maxResult: String
    ): Call<Playlist>

}