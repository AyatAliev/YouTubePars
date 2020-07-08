package com.example.geektechyoutubeparcer.ui.description

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.geektechyoutubeparcer.R
import com.example.geektechyoutubeparcer.model.NotesItem
import com.example.geektechyoutubeparcer.repository.DescApiPost
import com.example.geektechyoutubeparcer.ui.notes.NotesActivity
import kotlinx.android.synthetic.main.activity_description.*


class DescriptionActivity : AppCompatActivity() {

    private var notesItem: NotesItem? = null
    private var data: Intent? = null

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
            DescApiPost.getData(et_title.text.toString(),et_body.text.toString())
                notesItem?.title = et_title.text.toString()
                notesItem?.body = et_body.text.toString()
                data = Intent(this, NotesActivity::class.java).apply {
                    putExtra("change", notesItem)
                }
                setResult(RESULT_OK, data)
                finish()

        }
    }

}