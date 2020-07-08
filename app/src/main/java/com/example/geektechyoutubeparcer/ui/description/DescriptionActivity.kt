package com.example.geektechyoutubeparcer.ui.description

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.geektechyoutubeparcer.R
import com.example.geektechyoutubeparcer.model.Notes
import com.example.geektechyoutubeparcer.model.NotesItem
import com.example.geektechyoutubeparcer.model.PostChange
import com.example.geektechyoutubeparcer.network.NotesApi
import com.example.geektechyoutubeparcer.network.RetrofitClient
import com.example.geektechyoutubeparcer.ui.playlist.NotesActivity
import kotlinx.android.synthetic.main.activity_description.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class DescriptionActivity : AppCompatActivity() {

    private var notesItem: NotesItem? = null
    private var data : Intent? = null
    private var activity : NotesActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
        changeDescription()
        btn_click()
    }

    private fun changeDescription() {
        notesItem = intent.getSerializableExtra("notesItem") as NotesItem?
        et_title?.setText(notesItem?.title)
        et_body?.setText(notesItem?.body)
    }

    private fun btn_click() {
        principal_c.setOnClickListener {
            fetchNotes()
            notesItem?.title = et_title.text.toString()
            notesItem?.body = et_body.text.toString()
            data = Intent(this,NotesActivity::class.java).apply{
                putExtra("change", notesItem)
            }
            setResult(Activity.RESULT_OK,data)
            finish()
        }
    }

    fun fetchNotes(): LiveData<Notes?> {
        val apiService: NotesApi? = RetrofitClient.create()
        val data = MutableLiveData<Notes?>()
        var body : String = et_body.text.toString()
        var title : String = et_title.text.toString()
        apiService?.saveData(title,body)?.enqueue(object :
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