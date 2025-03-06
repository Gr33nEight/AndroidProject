package com.example.testapp.utils

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object SignIn: Screen("signIn")
    object SignUp: Screen("signUp")
}