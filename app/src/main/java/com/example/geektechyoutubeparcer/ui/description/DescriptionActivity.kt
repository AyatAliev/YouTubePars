package com.example.geektechyoutubeparcer.ui.description

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.geektechyoutubeparcer.R
import com.example.geektechyoutubeparcer.model.NotesItem
import com.example.geektechyoutubeparcer.repository.DescApiPost
import com.example.geektechyoutubeparcer.ui.notes.NotesActivity
import com.example.geektechyoutubeparcer.ui.notes.NotestViewModel
import kotlinx.android.synthetic.main.activity_description.*
import kotlin.math.log
import com.example.geektechyoutubeparcer.ui.description.DescriptionViewModel as DescriptionViewModel


class DescriptionActivity : AppCompatActivity() {

    private var notesItem: NotesItem? = null
    private var data: Intent? = null
    private var viewModel: DescriptionViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
        changeDescription()
        btnClick()
        viewModel = ViewModelProviders.of(this).get(DescriptionViewModel::class.java)
    }

    private fun setupToSubscribe() {
        viewModel?.fetchNotes()?.observe(this, Observer {

        })
    }
    private fun update() {
        viewModel?.update()?.observe(this, Observer {

        })
    }

    private fun changeDescription() {
        notesItem = intent.getSerializableExtra("notesItem") as NotesItem?
        et_title?.setText(notesItem?.title)
        et_body?.setText(notesItem?.body)
    }

    private fun btnClick() {
        principal_c.setOnClickListener {
            if (notesItem?.id == null) {
                DescApiPost.getData(et_title.text.toString(), et_body.text.toString())
                setupToSubscribe()
                notesItem =
                    NotesItem(title = et_title.text.toString(), body = et_body.text.toString())
                setResult(RESULT_OK, Intent().putExtra("change", notesItem))
                finish()
            } else {
                notesItem?.id?.let { it1 ->
                    DescApiPost.getData(et_title.text.toString(), et_body.text.toString(), it1) }
                update()
                notesItem =
                    NotesItem(title = et_title.text.toString(), body = et_body.text.toString())
                setResult(RESULT_OK, Intent().putExtra("change", notesItem))
                finish()
            }
        }
    }

    companion object {
        var id: Int? = null
        fun getData(id: Int) {
            this.id = id

        }
    }

}