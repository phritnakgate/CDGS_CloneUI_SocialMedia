package com.example.cloneui_socialmedia.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cloneui_socialmedia.fragments.PostsFragment
import com.example.cloneui_socialmedia.fragments.AboutFragment

class ProfileViewPagerAdapter (fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment = when(position){
        0 -> PostsFragment()
        1 -> AboutFragment()
        else -> throw IllegalArgumentException("Invalid position: $position")
    }

}