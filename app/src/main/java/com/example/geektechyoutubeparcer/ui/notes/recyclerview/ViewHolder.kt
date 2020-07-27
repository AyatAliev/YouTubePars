package com.example.geektechyoutubeparcer.ui.notes.recyclerview

import android.view.View
import android.widget.PopupMenu
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
            0 -> changeBackground(R.drawable.bg_tv)
            1 -> changeBackground(R.drawable.bg_tv1)
            2 -> changeBackground(R.drawable.bg_tv2)
            3 -> changeBackground(R.drawable.bg_tv3)

        }
    }

    private fun changeBackground(drawble: Int) {
        itemView.item_relative_layout.setBackgroundResource(drawble)
    }

}
interface Listener {
    fun onItemClick(position : Int,noteItem: NotesItem)
}

