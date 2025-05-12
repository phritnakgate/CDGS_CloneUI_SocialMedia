package com.example.cloneui_socialmedia

import android.content.Intent
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.cloneui_socialmedia.models.LoginViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.repeatOnLifecycle

class LoginActivity : AppCompatActivity() {

    private lateinit var appTitle : TextView
    private lateinit var loginButton : ConstraintLayout
    private lateinit var facebookButton : ConstraintLayout
    private lateinit var googleButton : ConstraintLayout
    private lateinit var xButton : ConstraintLayout
    private lateinit var signUpButton : TextView

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginProgress : ProgressBar

    private lateinit var emailForm : EditText
    private lateinit var passwordForm : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        initViews()
        appTitleShaderInit()
        buttonInit()

        lifecycleScope.launch{
            repeatOnLifecycle(androidx.lifecycle.Lifecycle.State.STARTED){
                loginViewModel.onLoginState.collect{ state ->
                    when(state){
                        is LoginViewModel.LoginState.Idle -> {
                            loginProgress.visibility = ProgressBar.GONE
                            loginButton.isEnabled = true
                        }
                        is LoginViewModel.LoginState.Loading -> {
                            loginProgress.visibility = ProgressBar.VISIBLE
                            loginButton.isEnabled = false
                        }
                        is LoginViewModel.LoginState.Success -> {
                            loginProgress.visibility = ProgressBar.GONE
                            loginButton.isEnabled = true
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        is LoginViewModel.LoginState.Invalid -> {
                            loginProgress.visibility = ProgressBar.GONE
                            loginButton.isEnabled = true
                            // Show error message
                            Toast.makeText(this@LoginActivity, state.message, Toast.LENGTH_SHORT).show()
                            // Reset the state to Idle
                            loginViewModel.resetLoginState()
                        }
                    }
                }
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initViews(){
        //Initialize Views
        appTitle = findViewById(R.id.textview_login_title)
        loginButton = findViewById(R.id.constraintlayout_login_signin_btn)
        facebookButton = findViewById(R.id.constraintlayout_login_facebook_btn)
        googleButton = findViewById(R.id.constraintlayout_login_google_btn)
        xButton = findViewById(R.id.constraintlayout_login_x_btn)
        signUpButton = findViewById(R.id.textview_login_signup_btn)
        loginProgress = findViewById(R.id.progressbar_login)
        emailForm = findViewById(R.id.edittext_login_email)
        passwordForm = findViewById(R.id.edittext_login_password)
        //Initialize ViewModel
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
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
           loginViewModel.login(emailForm.text.toString(), passwordForm.text.toString())
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