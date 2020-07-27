package com.example.geektechyoutubeparcer.model

import java.io.Serializable

data class NotesItem (
    var body: String? = null,
    val created_at: String? = null,
    val id: Int? = null,
    var title: String? = null,
    val color : Int? = null
) : Serializable