package com.example.geektechyoutubeparcer.ui.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geektechyoutubeparcer.R
import com.example.geektechyoutubeparcer.model.NotesItem
import com.example.geektechyoutubeparcer.ui.description.DescriptionActivity
import com.example.geektechyoutubeparcer.ui.description.DescriptionViewModel
import com.example.geektechyoutubeparcer.ui.notes.recyclerview.Listener
import com.example.geektechyoutubeparcer.ui.notes.Recyclerview.Adapter
import kotlinx.android.synthetic.main.activity_notes.*
import kotlinx.android.synthetic.main.item_notes.*
import kotlinx.android.synthetic.main.item_notes.view.*

class NotesActivity : AppCompatActivity(), Listener {

    private var notesItems = mutableListOf<NotesItem>()
    private var adapter: Adapter? = null
    private var viewModel: NotestViewModel? = null
    private var position: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        setRecyclerview()
        btn_click()
        viewModel = ViewModelProviders.of(this).get(NotestViewModel::class.java)
        setupToSubscribe()
    }

    private fun setupToSubscribe() {
        viewModel?.fetchPlaylist()?.observe(this, Observer {
            if (it != null) {
                adapter?.addItem(it)
                notesItems = it
            }
        })
    }

    private fun setRecyclerview() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        adapter =  Adapter(notesItems, this)
        recycler_view.adapter = adapter
    }

    private fun btn_click() {
        btn_frame_36.setOnClickListener {
            startActivityForResult(Intent(this, DescriptionActivity::class.java),101)
        }
    }

    override fun onItemClick(position: Int, notesItem: NotesItem) {
        this.position = position
        DescriptionActivity.getData(position)
        startActivityForResult(Intent(this, DescriptionActivity::class.java).apply {
            putExtra("notesItem", notesItem)
        }, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            val dto = data?.getSerializableExtra("change") as? NotesItem
            when (requestCode) {
               100 -> position?.let { adapter?.addItem(it, data?.getSerializableExtra("change") as? NotesItem) }
                101 -> adapter?.addItem(dto)
            }
        }
    }
}
