package com.example.testapp.views

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.testapp.view_models.ContactViewModel
import com.example.testapp.view_models.UserViewModel
import com.example.testapp.views.home.HomeView

@Composable
fun AuthView(
    navController: NavHostController,
    contactViewModel: ContactViewModel,
    context: Context
) {
    val userViewModel: UserViewModel = viewModel()
    LaunchedEffect(context) {
        userViewModel.checkLoginStatus(context)
    }

    val isLoggedIn = userViewModel.isUserLoggedIn.observeAsState(initial = false)

    if (isLoggedIn.value) {
        HomeView(contactViewModel, context) {
            userViewModel.logout(context)
        }
    } else {
        AppNavigation(navController, userViewModel, context)
    }
}