package com.example.geektechyoutubeparcer.ui.notes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geektechyoutubeparcer.model.Notes
import com.example.geektechyoutubeparcer.network.NotesApi
import com.example.geektechyoutubeparcer.network.RetrofitClient
import com.example.geektechyoutubeparcer.repository.DescApiPost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotestViewModel : ViewModel() {

    fun fetchPlaylist(): LiveData<Notes?> {
        return DescApiPost().fetchYoutubePlaylist()
    }





}