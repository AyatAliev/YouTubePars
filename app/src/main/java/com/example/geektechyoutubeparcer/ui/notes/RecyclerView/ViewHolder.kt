package com.example.geektechyoutubeparcer.ui.notes.RecyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.geektechyoutubeparcer.R
import com.example.geektechyoutubeparcer.model.NotesItem
import kotlinx.android.synthetic.main.item_notes.view.*

open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var listener : Listener? = null
    fun onBind(notesItem: NotesItem) {
        itemView.item_tv_title.text = notesItem.title
        itemView.item_tv_decs.text = notesItem.body
        setBackground(notesItem)
        itemView.setOnClickListener {
            listener?.onItemClick(adapterPosition,notesItem)
        }
    }

    private fun setBackground(notesItem: NotesItem){
        when (notesItem.color) {
            0 -> {
                itemView.item_relative_layout.setBackgroundResource(R.drawable.bg_tv)
            }
            1 -> {
                itemView.item_relative_layout.setBackgroundResource(R.drawable.bg_tv1)
            }
            2 -> {
                itemView.item_relative_layout.setBackgroundResource(R.drawable.bg_tv2)
            }
            3 -> {
                itemView.item_relative_layout.setBackgroundResource(R.drawable.bg_tv3)
            }
        }
    }
}
interface Listener {
    fun onItemClick(position : Int,noteItem: NotesItem)
}

