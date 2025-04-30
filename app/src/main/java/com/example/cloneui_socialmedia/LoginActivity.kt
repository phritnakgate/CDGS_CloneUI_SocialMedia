package com.example.cloneui_socialmedia

import android.content.Intent
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

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
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //Handle Social Media Buttons
        val facebookButton = findViewById<ConstraintLayout>(R.id.login_facebook_btn)
        facebookButton.setOnClickListener {
            Log.d("Facebook Button", "Clicked")
        }
        val googleButton = findViewById<ConstraintLayout>(R.id.login_google_btn)
        googleButton.setOnClickListener {
            Log.d("Google Button", "Clicked")
        }
        val xButton = findViewById<ConstraintLayout>(R.id.login_x_btn)
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