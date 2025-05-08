package com.example.cloneui_socialmedia.fragments

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.cloneui_socialmedia.R
import com.example.cloneui_socialmedia.adapters.ProfileViewPagerAdapter
import com.google.android.material.divider.MaterialDivider
import com.google.android.material.tabs.TabLayoutMediator

class ProfileFragment : Fragment(R.layout.fragment_profile) {

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

        //Handle Tabs & ViewPager2
        val postAboutAdapter = ProfileViewPagerAdapter(childFragmentManager, lifecycle)
        val viewPager = view.findViewById<androidx.viewpager2.widget.ViewPager2>(R.id.viewpager_profile_post_about_fragment)
        viewPager.adapter = postAboutAdapter

        val tabLayout = view.findViewById<com.google.android.material.tabs.TabLayout>(R.id.tabview_profile_about_post)
        TabLayoutMediator(tabLayout, viewPager) {tab, position ->

            when (position) {
                0 -> {
                    tab.text = "Posts"
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.profile_posts)
                }
                1 -> {
                    tab.text = "About"
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.profile_profile)
                }
            }
        }.attach()


        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.profile_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, 0)
            insets
        }
    }
}