package com.example.geektechyoutubeparcer.network

import com.example.geektechyoutubeparcer.model.Notes
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface NotesApi {

    @GET("api/v1/notes")
    fun fetchNotes() : Call<Notes>

    @POST("/api/v1/notes/")
    @FormUrlEncoded
    fun saveData(
        @Field("title") title: String?,
        @Field("body") body: String?
    ): Call<Notes>?

}