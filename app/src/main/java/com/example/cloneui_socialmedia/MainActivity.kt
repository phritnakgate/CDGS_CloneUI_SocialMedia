package com.example.cloneui_socialmedia

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, HomeFragmentActivity())
            .commit()

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavBar)
        bottomNav.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HomeFragmentActivity())
                        .commit()
                    true
                }
                R.id.nav_explore -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ExploreFragmentActivity())
                        .commit()
                    true
                }
                R.id.nav_add -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, AddFragmentActivity())
                        .commit()
                    true
                }
                R.id.nav_archive -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ArchiveFragmentActivity())
                        .commit()
                    true
                }
                R.id.nav_profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ProfileFragmentActivity())
                        .commit()
                    true
                }
                else -> false
            }
        }

        val notiBadge = bottomNav.getOrCreateBadge(R.id.nav_archive)
        notiBadge.isVisible = true
        notiBadge.number = 4
    }
}