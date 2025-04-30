package com.example.cloneui_socialmedia

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class ProfileFragmentActivity : Fragment(R.layout.fragment_profile) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Load Cover Image
        val coverImg = view.findViewById<AppCompatImageView>(R.id.profile_coverImg)
        Glide.with(this)
        .load("https://static.vecteezy.com/system/resources/previews/043/100/323/non_2x/ai-generated-background-banner-postcard-for-world-environment-day-photo.jpg")
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(coverImg)

        //Load Profile Image
        val profileImg = view.findViewById<AppCompatImageView>(R.id.profile_profileImgView)
        Glide.with(this)
        .load("https://miro.medium.com/v2/resize:fit:920/1*R5zt6Upx-ba905xLU6HNXw.gif")
        .placeholder(R.drawable.ic_launcher_foreground)
        .circleCrop()
        .into(profileImg)

        //Handle Clicking
        val editBtn = view.findViewById<ConstraintLayout>(R.id.profile_editBtn)
        editBtn.setOnClickListener {

        }
        val shareBtn = view.findViewById<ConstraintLayout>(R.id.profile_shareBtn)
        shareBtn.setOnClickListener {

        }
        val postBtn = view.findViewById<LinearLayout>(R.id.profile_postBtn)
        postBtn.setOnClickListener {

        }
        val aboutBtn = view.findViewById<LinearLayout>(R.id.profile_aboutBtn)
        aboutBtn.setOnClickListener {

        }
    }
}