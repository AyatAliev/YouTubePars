package com.example.geektechyoutubeparcer.model

import java.io.Serializable

data class NotesItem (
    var body: String,
    val created_at: String,
    val id: Int,
    var title: String,
    val color : Int
) : Serializable