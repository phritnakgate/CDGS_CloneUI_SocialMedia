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

    private lateinit var appTitle : TextView
    private lateinit var loginButton : ConstraintLayout
    private lateinit var facebookButton : ConstraintLayout
    private lateinit var googleButton : ConstraintLayout
    private lateinit var xButton : ConstraintLayout
    private lateinit var signUpButton : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        initViews()
        appTitleShaderInit()
        buttonInit()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initViews(){
        appTitle = findViewById(R.id.textview_login_title)
        loginButton = findViewById(R.id.constraintlayout_login_signin_btn)
        facebookButton = findViewById(R.id.constraintlayout_login_facebook_btn)
        googleButton = findViewById(R.id.constraintlayout_login_google_btn)
        xButton = findViewById(R.id.constraintlayout_login_x_btn)
        signUpButton = findViewById(R.id.textview_login_signup_btn)
    }

    //Title Gradient
    private fun appTitleShaderInit(){

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
    }

    //Button Clicks
    private fun buttonInit(){
        //Handle Login Button
        loginButton.setOnClickListener {
            Log.d("Login Button", "Clicked")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //Handle Social Media Buttons
        facebookButton.setOnClickListener {
            Log.d("Facebook Button", "Clicked")
        }
        googleButton.setOnClickListener {
            Log.d("Google Button", "Clicked")
        }
        xButton.setOnClickListener {
            Log.d("X Button", "Clicked")
        }

        //Handle Signup
        signUpButton.setOnClickListener {
            Log.d("Sign Up Button", "Clicked")
        }
    }
}