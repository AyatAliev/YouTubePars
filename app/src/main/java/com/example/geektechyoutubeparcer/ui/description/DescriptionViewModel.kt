package com.example.geektechyoutubeparcer.ui.description

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.geektechyoutubeparcer.model.Notes
import com.example.geektechyoutubeparcer.repository.DescApiPost

class DescriptionViewModel : ViewModel() {

    fun fetchNotes(): LiveData<Notes?> {
        return DescApiPost().fetchNotes()
    }

    fun update(): LiveData<Notes?> {
        return DescApiPost().update()
    }





}