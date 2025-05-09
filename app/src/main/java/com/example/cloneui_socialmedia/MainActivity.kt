package com.example.cloneui_socialmedia

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.cloneui_socialmedia.fragments.AddFragment
import com.example.cloneui_socialmedia.fragments.ArchiveFragment
import com.example.cloneui_socialmedia.fragments.ExploreFragment
import com.example.cloneui_socialmedia.fragments.HomeFragment
import com.example.cloneui_socialmedia.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        initViews()
        showFragment(HomeFragment())
        bottomNavHandler()
        showArchiveBadge(4)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, 0)
            insets
        }
    }

    //Init Views
    private fun initViews(){
        bottomNav = findViewById(R.id.bottomnavigation_main)
    }
    //Handle Showing Fragments
    private fun showFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.framelayout_main_fragment, fragment)
            .commit()
    }
    //Handle Bottom Navigation
    private fun bottomNavHandler(){
        bottomNav.setOnItemSelectedListener { item ->
            val selectedFragment = when(item.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_explore -> ExploreFragment()
                R.id.nav_add -> AddFragment()
                R.id.nav_archive -> ArchiveFragment()
                R.id.nav_profile -> ProfileFragment()
                else -> null
            }
            selectedFragment?.let{
                showFragment(it)
                true
            } ?: false
        }
    }
    //Handle Notification Badge
    private fun showArchiveBadge(archiveCnt: Int){
        bottomNav.getOrCreateBadge(R.id.nav_archive).apply {
            isVisible = true
            number = archiveCnt
        }

    }


}