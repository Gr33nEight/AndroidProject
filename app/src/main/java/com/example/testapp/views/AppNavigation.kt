package com.example.testapp.views

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testapp.utils.Screen
import com.example.testapp.view_models.ContactViewModel
import com.example.testapp.views.home.HomeView

@Composable
fun AppNavigation(navController: NavHostController, contactViewModel: ContactViewModel = viewModel()) {

    NavHost(navController = navController, startDestination = Screen.SignIn.route) {
        composable(Screen.Home.route) {
            HomeView(contactViewModel, navController)
        }
        composable(Screen.SignIn.route) {
            SignInView(navController)
        }
        composable(Screen.SignUp.route) {
            SignUpView(navController)
        }
    }
}