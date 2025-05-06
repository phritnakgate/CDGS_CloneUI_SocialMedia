package com.example.cloneui_socialmedia

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.divider.MaterialDivider

class ProfileFragmentActivity : Fragment(R.layout.fragment_profile) {

    //Function to handle color change
    private fun highlightTab(isPostTab : Boolean) {
        val postBtnImg = view?.findViewById<AppCompatImageView>(R.id.profile_postBtnImg)
        val postText = view?.findViewById<TextView>(R.id.profile_postBtnText)
        val postDiv = view?.findViewById<MaterialDivider>(R.id.profile_divUnderTabPost)
        val aboutBtnImg = view?.findViewById<AppCompatImageView>(R.id.profile_aboutBtnImg)
        val aboutText = view?.findViewById<TextView>(R.id.profile_aboutBtnText)
        val aboutDiv = view?.findViewById<MaterialDivider>(R.id.profile_divUnderTabAbout)
        if(isPostTab){
            postText?.setTextColor(ContextCompat.getColor(requireContext(), R.color.secondaryColor))
            postDiv?.dividerColor = ContextCompat.getColor(requireContext(), R.color.secondaryColor)
            postBtnImg?.imageTintList = ContextCompat.getColorStateList(requireContext(), R.color.secondaryColor)
            aboutText?.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            aboutDiv?.dividerColor = ContextCompat.getColor(requireContext(), R.color.disabledColorA30)
            aboutBtnImg?.imageTintList = ContextCompat.getColorStateList(requireContext(), R.color.black)
        }else{
            postText?.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            postDiv?.dividerColor = ContextCompat.getColor(requireContext(), R.color.disabledColorA30)
            postBtnImg?.imageTintList = ContextCompat.getColorStateList(requireContext(), R.color.black)
            aboutText?.setTextColor(ContextCompat.getColor(requireContext(), R.color.secondaryColor))
            aboutDiv?.dividerColor = ContextCompat.getColor(requireContext(), R.color.secondaryColor)
            aboutBtnImg?.imageTintList = ContextCompat.getColorStateList(requireContext(), R.color.secondaryColor)
        }
    }


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
            parentFragmentManager.beginTransaction()
                .replace(R.id.profile_postAboutFragment, PostsFragmentActivity())
                .commit()
            highlightTab(true)
        }
        val aboutBtn = view.findViewById<LinearLayout>(R.id.profile_aboutBtn)
        aboutBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.profile_postAboutFragment, AboutFragmentActivity())
                .commit()
            highlightTab(false)
        }
    }
}