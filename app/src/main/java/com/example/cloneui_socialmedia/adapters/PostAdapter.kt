package com.example.cloneui_socialmedia.adapters

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.cloneui_socialmedia.R
import com.example.cloneui_socialmedia.models.PostData
import com.example.cloneui_socialmedia.utils.PostBinder
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

class PostAdapter : ListAdapter<PostData, PostAdapter.ViewHolder>(PostDiffCallback()) {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = getItem(position)
        PostBinder.bind(holder.itemView, post)
    }
}

class PostDiffCallback : DiffUtil.ItemCallback<PostData>() {
    override fun areItemsTheSame(oldItem: PostData, newItem: PostData): Boolean {
        return oldItem.userId == newItem.userId && oldItem.datePosted == newItem.datePosted
    }

    override fun areContentsTheSame(oldItem: PostData, newItem: PostData): Boolean {
        return oldItem == newItem
    }
}
