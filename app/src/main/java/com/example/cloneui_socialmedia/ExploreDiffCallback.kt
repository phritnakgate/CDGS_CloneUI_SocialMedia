package com.example.cloneui_socialmedia

import androidx.recyclerview.widget.DiffUtil

object ExploreDiffCallback : DiffUtil.ItemCallback<ExploreItem>() {
    override fun areItemsTheSame(oldItem: ExploreItem, newItem: ExploreItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ExploreItem, newItem: ExploreItem): Boolean {
        return oldItem == newItem
    }
}
