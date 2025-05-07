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
        private val glide : RequestManager = Glide.with(itemView.context)
        private val profileImg : AppCompatImageView? = itemView.findViewById(R.id.home_postProfileImg)
        private val username : TextView? = itemView.findViewById(R.id.home_postUsername)
        private val userId : TextView? = itemView.findViewById(R.id.home_postUserId)
        private val postDate : TextView? = itemView.findViewById(R.id.home_postDate)
        private val postImg : AppCompatImageView? = itemView.findViewById(R.id.home_postImg)
        private val postDesc : TextView? = itemView.findViewById(R.id.home_postDesc)
        private val postLikes : TextView? = itemView.findViewById(R.id.home_postLikeCount)
        private val postLikeIcon : AppCompatImageView? = itemView.findViewById(R.id.home_postLikeIcon)
        private val postLikeBtn : ConstraintLayout? = itemView.findViewById(R.id.home_postLikeButton)
        private val postComments : TextView? = itemView.findViewById(R.id.home_postCommentCount)
        private val postShares : TextView? = itemView.findViewById(R.id.home_postShareCount)
        private val postBookmarks : TextView? = itemView.findViewById(R.id.home_postBookmarkCount)
        private val postBookmarkIcon : AppCompatImageView? = itemView.findViewById(R.id.home_postBookmarkIcon)
        private val postBookmarkBtn : ConstraintLayout? = itemView.findViewById(R.id.home_postBookmarkButton)
        private val openDrawerBtn : ConstraintLayout? = itemView.findViewById(R.id.home_openDrawerBtn)

        fun bind(post: PostData){
            if (profileImg != null) {
                glide.load(post.profileImgUrl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .circleCrop()
                    .into(profileImg)
            }

            username?.text = post.username
            userId?.text = "@${post.userId}"

            val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val totalDatePosted = Date().time - formatter.parse(post.datePosted)!!.time
            postDate?.text = "${TimeUnit.MILLISECONDS.toDays(totalDatePosted)} day(s) ago"

            if (postImg != null) {
                glide.load(post.postImage)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(postImg)
            }

            val spannable = SpannableString(post.postDescription)
            "#\\w+".toRegex().findAll(post.postDescription).forEach {
                val color = ContextCompat.getColor(itemView.context, R.color.secondaryColor)
                spannable.setSpan(ForegroundColorSpan(color), it.range.first, it.range.last + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            postDesc?.text = spannable

            postLikes?.text = post.postLikes.toString()
            postComments?.text = post.postComments.toString()
            postShares?.text = post.postShares.toString()
            postBookmarks?.text = post.postBookmark.toString()

            postLikeIcon?.setImageResource(if (post.postLiked) R.drawable.post_like_pressed else R.drawable.post_like)
            postBookmarkIcon?.setImageResource(if (post.postBookmarked) R.drawable.post_bookmark_pressed else R.drawable.post_bookmark)

            postLikeBtn?.setOnClickListener {
                post.postLiked = !post.postLiked
                post.postLikes += if (post.postLiked) 1 else -1
                postLikes?.text = post.postLikes.toString()
                postLikeIcon?.setImageResource(if (post.postLiked) R.drawable.post_like_pressed else R.drawable.post_like)
            }

            postBookmarkBtn?.setOnClickListener {
                post.postBookmarked = !post.postBookmarked
                post.postBookmark += if (post.postBookmarked) 1 else -1
                postBookmarks?.text = post.postBookmark.toString()
                postBookmarkIcon?.setImageResource(if (post.postBookmarked) R.drawable.post_bookmark_pressed else R.drawable.post_bookmark)
            }

            val bottomSheet = BottomSheetDialog(itemView.context)
            val bottomSheetView = LayoutInflater.from(itemView.context).inflate(R.layout.post_bottomsheet, null)
            openDrawerBtn?.setOnClickListener {
                bottomSheet.setContentView(bottomSheetView)
                bottomSheet.show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_post,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemLists[position]
        holder.bind(item)

    }

    override fun getItemCount(): Int {
        return itemLists.size
    }


}