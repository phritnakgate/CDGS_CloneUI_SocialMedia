package com.example.cloneui_socialmedia.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cloneui_socialmedia.R
import com.example.cloneui_socialmedia.adapters.AboutAdapter
import com.example.cloneui_socialmedia.models.AboutData

class AboutFragment : Fragment(R.layout.fragment_about) {

    private val aboutData = mutableListOf(
        AboutData("Gender", "Male", R.drawable.about_gender),
        AboutData("Birth Of Date", "10 November 2024", R.drawable.about_bdate),
        AboutData("Language", "Thai, English", R.drawable.about_language)
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv = view.findViewById<RecyclerView>(R.id.profile_aboutRecycler)
        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv.adapter = AboutAdapter(aboutData)
        rv.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        rv.setHasFixedSize(true)
    }
}