package com.example.cloneui_socialmedia.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)

    val onLoginState : StateFlow<LoginState> = _loginState //Used in MainActivity of Login Page

    fun login(email: String, password: String){
        viewModelScope.launch{
            // Simulate a network call 1s delay
            _loginState.value = LoginState.Loading
            delay(1000L)

            if(email == "test@gmail.com" && password == "1234"){
                _loginState.value = LoginState.Success
            } else {
                _loginState.value = LoginState.Invalid("Invalid email or password")
            }
        }
    }


    sealed class LoginState {
        data object Idle : LoginState()
        data object Loading : LoginState()
        data object Success : LoginState()
        data class Invalid(val message: String) : LoginState()
    }
}