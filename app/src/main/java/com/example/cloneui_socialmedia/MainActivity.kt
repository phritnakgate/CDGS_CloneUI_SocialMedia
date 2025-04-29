package com.example.cloneui_socialmedia

import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Title Gradient
        val appTitle = findViewById<TextView>(R.id.login_title)
        val titleWidth = appTitle.paint.measureText(appTitle.text.toString())
        val titleShader = LinearGradient(
            0f,0f,titleWidth,appTitle.textSize,
            intArrayOf(
                ContextCompat.getColor(this, R.color.signInGradient1),
                ContextCompat.getColor(this, R.color.signInGradient2),
            ),
            null,
            Shader.TileMode.CLAMP
        )
        appTitle.paint.shader = titleShader

        //Handle Login Button
        val loginButton = findViewById<ConstraintLayout>(R.id.login_signInBtn)
        loginButton.setOnClickListener {
            Log.d("Login Button", "Clicked")
        }

        //Handle Social Media Buttons
        val facebookButton = findViewById<ConstraintLayout>(R.id.login_facebookBtn)
        facebookButton.findViewById<ImageView>(R.id.login_social_btn_pic).setImageResource(R.drawable.facebook_logo)
        facebookButton.setOnClickListener {
            Log.d("Facebook Button", "Clicked")
        }
        val googleButton = findViewById<ConstraintLayout>(R.id.login_googleBtn)
        googleButton.findViewById<ImageView>(R.id.login_social_btn_pic).setImageResource(R.drawable.google_logo)
        googleButton.setOnClickListener {
            Log.d("Google Button", "Clicked")
        }
        val xButton = findViewById<ConstraintLayout>(R.id.login_xBtn)
        xButton.findViewById<ImageView>(R.id.login_social_btn_pic).setImageResource(R.drawable.x_logo)
        xButton.setOnClickListener {
            Log.d("X Button", "Clicked")
        }
        //Handle Signup
        val signUpButton = findViewById<TextView>(R.id.login_signUpBtn)
        signUpButton.setOnClickListener {
            Log.d("Sign Up Button", "Clicked")
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}