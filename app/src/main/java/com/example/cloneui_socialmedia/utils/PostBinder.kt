package com.example.cloneui_socialmedia.utils

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.cloneui_socialmedia.R
import com.example.cloneui_socialmedia.models.PostData
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object PostBinder {
    fun bind(view: View, post: PostData) {
        val glide: RequestManager = Glide.with(view.context)

        val profileImg = view.findViewById<AppCompatImageView>(R.id.home_postProfileImg)
        val username = view.findViewById<TextView>(R.id.home_postUsername)
        val userId = view.findViewById<TextView>(R.id.home_postUserId)
        val postDate = view.findViewById<TextView>(R.id.home_postDate)
        val postImg = view.findViewById<AppCompatImageView>(R.id.home_postImg)
        val postDesc = view.findViewById<TextView>(R.id.home_postDesc)
        val postLikes = view.findViewById<TextView>(R.id.home_postLikeCount)
        val postLikeIcon = view.findViewById<AppCompatImageView>(R.id.home_postLikeIcon)
        val postLikeBtn = view.findViewById<ConstraintLayout>(R.id.home_postLikeButton)
        val postComments = view.findViewById<TextView>(R.id.home_postCommentCount)
        val postShares = view.findViewById<TextView>(R.id.home_postShareCount)
        val postBookmarks = view.findViewById<TextView>(R.id.home_postBookmarkCount)
        val postBookmarkIcon = view.findViewById<AppCompatImageView>(R.id.home_postBookmarkIcon)
        val postBookmarkBtn = view.findViewById<ConstraintLayout>(R.id.home_postBookmarkButton)
        val openDrawerBtn = view.findViewById<ConstraintLayout>(R.id.home_openDrawerBtn)

        glide.load(post.profileImgUrl).placeholder(R.drawable.ic_launcher_background).circleCrop().into(profileImg)
        username.text = post.username
        userId.text = "@${post.userId}"

        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val totalDatePosted = Date().time - formatter.parse(post.datePosted)!!.time
        postDate.text = "${TimeUnit.MILLISECONDS.toDays(totalDatePosted)} day(s) ago"

        glide.load(post.postImage).placeholder(R.drawable.ic_launcher_background).into(postImg)

        val spannable = SpannableString(post.postDescription)
        "#\\w+".toRegex().findAll(post.postDescription).forEach {
            val color = ContextCompat.getColor(view.context, R.color.secondaryColor)
            spannable.setSpan(ForegroundColorSpan(color), it.range.first, it.range.last + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        postDesc.text = spannable

        postLikes.text = post.postLikes.toString()
        postComments.text = post.postComments.toString()
        postShares.text = post.postShares.toString()
        postBookmarks.text = post.postBookmark.toString()

        postLikeIcon.setImageResource(if (post.isPostLiked) R.drawable.post_like_pressed else R.drawable.post_like)
        postBookmarkIcon.setImageResource(if (post.isPostBookmarked) R.drawable.post_bookmark_pressed else R.drawable.post_bookmark)

        postLikeBtn.setOnClickListener {
            post.isPostLiked = !post.isPostLiked
            post.postLikes += if (post.isPostLiked) 1 else -1
            postLikes.text = post.postLikes.toString()
            postLikeIcon.setImageResource(if (post.isPostLiked) R.drawable.post_like_pressed else R.drawable.post_like)
        }

        postBookmarkBtn.setOnClickListener {
            post.isPostBookmarked = !post.isPostBookmarked
            post.postBookmark += if (post.isPostBookmarked) 1 else -1
            postBookmarks.text = post.postBookmark.toString()
            postBookmarkIcon.setImageResource(if (post.isPostBookmarked) R.drawable.post_bookmark_pressed else R.drawable.post_bookmark)
        }

        val bottomSheet = BottomSheetDialog(view.context)
        val bottomSheetView = View.inflate(view.context, R.layout.post_bottomsheet, null)
        openDrawerBtn.setOnClickListener {
            bottomSheet.setContentView(bottomSheetView)
            bottomSheet.show()
        }
    }
}
