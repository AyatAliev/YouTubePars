package com.example.geektechyoutubeparcer.ui.notes.Recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.geektechyoutubeparcer.R
import com.example.geektechyoutubeparcer.model.NotesItem
import com.example.geektechyoutubeparcer.ui.notes.recyclerview.Listener
import com.example.geektechyoutubeparcer.ui.notes.recyclerview.ViewHolder
import kotlinx.android.synthetic.main.item_notes.*
import kotlinx.android.synthetic.main.item_notes.view.*


class Adapter(
    private val list: MutableList<NotesItem> = mutableListOf(),
    private val listener: Listener
) : RecyclerView.Adapter<ViewHolder>() {

    fun addItem(data: MutableList<NotesItem>) {
        list.addAll(data)
        notifyDataSetChanged()
    }

    fun addItem(data: NotesItem?) {
        data?.let { list.add(0,it) }
        notifyDataSetChanged()
    }

    fun addItem(pos: Int, data: NotesItem?) {
        list.removeAt(pos)
        data?.let { list.add(pos, it) }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_notes, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
        holder.listener = listener
        holder.itemView.ic_dots.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu( holder.itemView.context, holder.itemView.ic_dots)
            popupMenu.menuInflater.inflate(R.menu.popup_menu,popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.action_delete -> {
                        position.let { it1 -> list.removeAt(it1) }
                        notifyDataSetChanged()
                    }

                }
                true
            }
            popupMenu.show()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

