package com.example.geektechyoutubeparcer.ui.playlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geektechyoutubeparcer.model.Notes
import com.example.geektechyoutubeparcer.network.NotesApi
import com.example.geektechyoutubeparcer.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistViewModel : ViewModel() {

    fun fetchPlaylist(): LiveData<Notes?> {
        return fetchYoutubePlaylist()
    }

    private  var apiService: NotesApi? = null
    fun fetchYoutubePlaylist(): LiveData<Notes?> {
        apiService = RetrofitClient.create()
        val data = MutableLiveData<Notes?>()
        apiService?.fetchNotes()?.enqueue(object :
            Callback<Notes> {
            override fun onFailure(call: Call<Notes>, t: Throwable) {
                //500.. и выше
                data.value = null
                Log.v("ololo",t.message.toString())
            }

            override fun onResponse(call: Call<Notes>, response: Response<Notes>) {
                //404 - не найдено, 401 - нет доступа, 403 - токен истек
                data.value = response.body()
                Log.v("ololo",response.code().toString())

            }
        })
        return data
    }



}