package com.example.geektechyoutubeparcer.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.geektechyoutubeparcer.model.Notes
import com.example.geektechyoutubeparcer.network.NotesApi
import com.example.geektechyoutubeparcer.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DescApiPost {

    private  var apiService: NotesApi? = null
    fun fetchYoutubePlaylist(): LiveData<Notes?> {
        apiService = RetrofitClient.create()
        val data = MutableLiveData<Notes?>()
        apiService?.fetchNotes()?.enqueue(object :
            Callback<Notes> {
            override fun onFailure(call: Call<Notes>, t: Throwable) {
                //500.. и выше
                data.value = null
            }

            override fun onResponse(call: Call<Notes>, response: Response<Notes>) {
                //404 - не найдено, 401 - нет доступа, 403 - токен истек
                data.value = response.body()

            }
        })
        return data
    }

    fun fetchNotes(): LiveData<Notes?> {
        val apiService: NotesApi? = RetrofitClient.create()
        val data = MutableLiveData<Notes?>()
        apiService?.saveData(title, body)?.enqueue(object :
            Callback<Notes> {
            override fun onFailure(call: Call<Notes>, t: Throwable) {
                //500.. и выше
                data.value = null
            }

            override fun onResponse(call: Call<Notes>, response: Response<Notes>) {
                //404 - не найдено, 401 - нет доступа, 403 - токен истек
                data.value = response.body()
                Log.v("ololo", response.code().toString())

            }
        })
        return data
    }

    fun update(): LiveData<Notes?> {
        val apiService: NotesApi? = RetrofitClient.create()
        val data = MutableLiveData<Notes?>()
        apiService?.updateData(title, body,id)?.enqueue(object :
            Callback<Notes> {
            override fun onFailure(call: Call<Notes>, t: Throwable) {
                //500.. и выше
                data.value = null
            }

            override fun onResponse(call: Call<Notes>, response: Response<Notes>) {
                //404 - не найдено, 401 - нет доступа, 403 - токен истек
                data.value = response.body()
                Log.v("ololo", response.code().toString())

            }
        })
        return data
    }

    companion object {
        var title: String? = null
        var body: String? = null
        var id: Int? = null
        fun getData(title: String, body: String) {
            this.body = body
            this.title = title
        }

        fun getData(title: String, body: String, id: Int) {
            this.body = body
            this.title = title
            this.id = id
        }
    }

}