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
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.cloneui_socialmedia.models.PostData
import com.example.cloneui_socialmedia.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

class PostAdapter(private var itemLists : MutableList<PostData>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val glide : RequestManager = Glide.with(itemView.context)
        val profileImg : AppCompatImageView? = itemView.findViewById(R.id.home_postProfileImg)
        val username : TextView? = itemView.findViewById(R.id.home_postUsername)
        val userId : TextView? = itemView.findViewById(R.id.home_postUserId)
        val postDate : TextView? = itemView.findViewById(R.id.home_postDate)
        val postImg : AppCompatImageView? = itemView.findViewById(R.id.home_postImg)
        val postDesc : TextView? = itemView.findViewById(R.id.home_postDesc)
        val postLikes : TextView? = itemView.findViewById(R.id.home_postLikeCount)
        val postLikeIcon : AppCompatImageView? = itemView.findViewById(R.id.home_postLikeIcon)
        val postLikeBtn : ConstraintLayout? = itemView.findViewById(R.id.home_postLikeButton)
        val postComments : TextView? = itemView.findViewById(R.id.home_postCommentCount)
        val postShares : TextView? = itemView.findViewById(R.id.home_postShareCount)
        val postBookmarks : TextView? = itemView.findViewById(R.id.home_postBookmarkCount)
        val postBookmarkIcon : AppCompatImageView? = itemView.findViewById(R.id.home_postBookmarkIcon)
        val postBookmarkBtn : ConstraintLayout? = itemView.findViewById(R.id.home_postBookmarkButton)
        val openDrawerBtn : ConstraintLayout? = itemView.findViewById(R.id.home_openDrawerBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_post,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemLists[position]
        //Handle Post Top Section
        holder.profileImg
        holder.glide.load(item.profileImgUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .circleCrop()
            .into(holder.profileImg!!)
        holder.username?.text = item.username
        holder.userId?.text = "@${item.userId}"
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val totalDatePosted = Date().time - formatter.parse(item.datePosted)!!.time
        holder.postDate?.text = "${TimeUnit.MILLISECONDS.toDays(totalDatePosted)} day(s) ago"

        //Handle Post Image
        holder.postImg
        holder.glide.load(item.postImage)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.postImg!!)

        //Handle Post Description
        val desc = item.postDescription
        val spannable = SpannableString(desc)
        val pattern = "#\\w+".toRegex()
        pattern.findAll(desc).forEach { matchResult ->
            val startHashTag = matchResult.range.first
            val endHashTag = matchResult.range.last + 1
            val color = ContextCompat.getColor(holder.itemView.context, R.color.secondaryColor)
            spannable.setSpan(
                ForegroundColorSpan(color)
                , startHashTag, endHashTag, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        holder.postDesc?.text = spannable

        //Handle Post Bottom Section
        holder.postLikes?.text = item.postLikes.toString()
        holder.postComments?.text = item.postComments.toString()
        holder.postBookmarks?.text = item.postBookmark.toString()
        holder.postShares?.text = item.postShares.toString()

        //Handle Interaction of Like and Bookmark
        holder.postLikeBtn?.setOnClickListener {
            item.postLiked = !item.postLiked
            if (item.postLiked) {
                item.postLikes += 1
                holder.postLikeIcon?.setImageResource(R.drawable.post_like_pressed)
            } else {
                item.postLikes -= 1
                holder.postLikeIcon?.setImageResource(R.drawable.post_like)
            }
            holder.postLikes?.text = item.postLikes.toString()
        }
        holder.postBookmarkBtn?.setOnClickListener {
            item.postBookmarked = !item.postBookmarked
            if (item.postBookmarked) {
                item.postBookmark += 1
                holder.postBookmarkIcon?.setImageResource(R.drawable.post_bookmark_pressed)
            } else {
                item.postBookmark -= 1
                holder.postBookmarkIcon?.setImageResource(R.drawable.post_bookmark)
            }
            holder.postBookmarks?.text = item.postBookmark.toString()
        }

        //Handle BottomDrawer
        val bottomSheet = BottomSheetDialog(holder.itemView.context)
        val bottomSheetView = LayoutInflater.from(holder.itemView.context).inflate(R.layout.post_bottomsheet, null)
        holder.openDrawerBtn?.setOnClickListener {
            bottomSheet.setContentView(bottomSheetView)
            bottomSheet.show()
        }
    }

    override fun getItemCount(): Int {
        return itemLists.size
    }


}