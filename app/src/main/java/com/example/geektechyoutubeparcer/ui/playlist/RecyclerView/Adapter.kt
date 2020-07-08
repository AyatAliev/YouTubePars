package com.example.geektechyoutubeparcer.ui.playlist.Recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.geektechyoutubeparcer.R
import com.example.geektechyoutubeparcer.model.NotesItem
import com.example.geektechyoutubeparcer.ui.playlist.NotesActivity
import com.example.geektechyoutubeparcer.ui.playlist.RecyclerView.Listener
import com.example.geektechyoutubeparcer.ui.playlist.RecyclerView.ViewHolder


class Adapter(
    private val list: MutableList<NotesItem> = mutableListOf(),
    private val listener: Listener
) : RecyclerView.Adapter<ViewHolder>() {

    fun addItem(data: MutableList<NotesItem>) {
        list.addAll(data)
        notifyDataSetChanged()
    }

    fun addItem(data: NotesItem?) {
        data?.let { list.add(it) }
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
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

