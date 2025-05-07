package com.example.cloneui_socialmedia

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cloneui_socialmedia.fragments.AddFragment
import com.example.cloneui_socialmedia.fragments.ArchiveFragment
import com.example.cloneui_socialmedia.fragments.ExploreFragment
import com.example.cloneui_socialmedia.fragments.HomeFragment
import com.example.cloneui_socialmedia.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, HomeFragment())
            .commit()

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavBar)
        bottomNav.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HomeFragment())
                        .commit()
                    true
                }
                R.id.nav_explore -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ExploreFragment())
                        .commit()
                    true
                }
                R.id.nav_add -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, AddFragment())
                        .commit()
                    true
                }
                R.id.nav_archive -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ArchiveFragment())
                        .commit()
                    true
                }
                R.id.nav_profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ProfileFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }

        val notiBadge = bottomNav.getOrCreateBadge(R.id.nav_archive)
        notiBadge.isVisible = true
        notiBadge.number = 4

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, 0)
            insets
        }
    }
}