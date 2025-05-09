package com.example.cloneui_socialmedia.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.cloneui_socialmedia.R
import com.example.cloneui_socialmedia.adapters.ProfileViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var coverImg : AppCompatImageView
    private lateinit var profileImg :  AppCompatImageView
    private lateinit var editBtn : ConstraintLayout
    private lateinit var shareBtn : ConstraintLayout
    private lateinit var viewPager : androidx.viewpager2.widget.ViewPager2
    private lateinit var tabLayout : com.google.android.material.tabs.TabLayout
    
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        loadImage()
        tabViewInit()
        buttonClickHandler()

        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.profile_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, 0)
            insets
        }
    }
    
    private fun initViews(){
        coverImg = requireView().findViewById(R.id.compatimageview_profile_cover_img)
        profileImg = requireView().findViewById(R.id.compatimageview_profile_profile_img)
        editBtn = requireView().findViewById(R.id.constraintlayout_profile_edit_btn)
        shareBtn = requireView().findViewById(R.id.constraintlayout_profile_share_btn)
        viewPager = requireView().findViewById(R.id.viewpager_profile_post_about_fragment)
        tabLayout = requireView().findViewById(R.id.tabview_profile_about_post)
    }

    private fun loadImage(){
        //Load Cover Image
        Glide.with(this)
            .load("https://static.vecteezy.com/system/resources/previews/043/100/323/non_2x/ai-generated-background-banner-postcard-for-world-environment-day-photo.jpg")
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(coverImg)

        //Load Profile Image
        Glide.with(this)
            .load("https://miro.medium.com/v2/resize:fit:920/1*R5zt6Upx-ba905xLU6HNXw.gif")
            .placeholder(R.drawable.ic_launcher_foreground)
            .circleCrop()
            .into(profileImg)

    }

    //Handle Tabs & ViewPager2
    private fun tabViewInit(){
        val postAboutAdapter = ProfileViewPagerAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = postAboutAdapter
        TabLayoutMediator(tabLayout, viewPager) {tab, position ->
            when (position) {
                0 -> {
                    tab.text = ContextCompat.getString(requireContext(), R.string.profile_tabview_post_title)
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.profile_posts)
                }
                1 -> {
                    tab.text = ContextCompat.getString(requireContext(), R.string.profile_tabview_about_title)
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.profile_profile)
                }
            }
        }.attach()
    }

    private fun buttonClickHandler(){
        editBtn.setOnClickListener {}
        shareBtn.setOnClickListener {}
    }
}