package com.example.geektechyoutubeparcer.ui.playlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.geektechyoutubeparcer.R
import com.example.geektechyoutubeparcer.model.Playlist
import com.example.geektechyoutubeparcer.network.RetrofitClient
import com.example.geektechyoutubeparcer.network.YoutubeAPi
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class PlaylistActivity : AppCompatActivity() {


    private var viewModel: PlaylistViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(PlaylistViewModel::class.java)
        fetchPlaylist()
    }


    fun fetchPlaylist(): LiveData<Playlist?>? {
        return this!!.fetchYoutubePlaylist()
    }
    private  var apiService: YoutubeAPi? = null


    fun fetchYoutubePlaylist(): LiveData<Playlist?>? {
        var data : LiveData<Playlist?>? = null
        btn_click.setOnClickListener {
            apiService = RetrofitClient.create()
            val data = MutableLiveData<Playlist?>()
            apiService?.fetchAllPlaylists(edit_body.text.toString(), edit_title.text.toString())
                ?.enqueue(object :
                    Callback<Playlist> {
                    override fun onFailure(call: Call<Playlist>, t: Throwable) {
                        //500.. и выше
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT)
                        Log.v("ololo",t.message.toString())
                    }

                    override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                        //404 - не найдено, 401 - нет доступа, 403 - токен истек
//                data.value = response.body()
                        Toast.makeText(applicationContext, response.code(), Toast.LENGTH_SHORT)
                            .show()
                        Log.v("ololo",response.code().toString())
                    }

                })


        }
        return data
    }

    //создать свой апи кей
    //добавить в класс playlist поле "items"
    //отрисовать всё в адаптере
    //сделать переход на новую активити и передаете туда id и её отображаете тостом

}
