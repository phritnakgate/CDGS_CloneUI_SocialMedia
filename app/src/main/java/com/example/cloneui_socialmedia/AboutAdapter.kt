package com.example.cloneui_socialmedia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView

class AboutAdapter(private var itemLists : MutableList<AboutData>) : RecyclerView.Adapter<AboutAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon : AppCompatImageView? = itemView.findViewById(R.id.profile_aboutCardIcon)
        val title : TextView? = itemView.findViewById(R.id.profile_aboutCardTitle)
        val desc : TextView? = itemView.findViewById(R.id.profile_aboutCardDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_about,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemLists[position]
        holder.icon?.setImageResource(item.icon)
        holder.title?.text = item.title
        holder.desc?.text = item.description
    }

    override fun getItemCount(): Int {
        return itemLists.size
    }
}