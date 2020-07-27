package com.example.geektechyoutubeparcer.network

import com.example.geektechyoutubeparcer.model.Notes
import retrofit2.Call
import retrofit2.http.*
import java.text.FieldPosition


interface NotesApi {

    @GET("api/v1/note-list/")
    fun fetchNotes(): Call<Notes>

    @POST("api/v1/note-create/")
    @FormUrlEncoded
    fun saveData(
        @Field("title") title: String?,
        @Field("body") body: String?
    ): Call<Notes>?


    @POST("api/v1/note-upgrade/{id}")
    @FormUrlEncoded
    fun updateData(
        @Field("title") title: String?,
        @Field("body") body: String?,
        @Path("id") id: Int?
    ): Call<Notes>?


    @DELETE("api/v1/note-delete/{id}")
    @FormUrlEncoded
    fun deleteData(
        @Path("id") id: Int?
    ): Call<Notes>?

}