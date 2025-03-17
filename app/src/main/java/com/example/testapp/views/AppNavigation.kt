package com.example.testapp.views

import android.content.Context
import android.util.Log
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testapp.utils.Screen
import com.example.testapp.view_models.ContactViewModel
import com.example.testapp.view_models.UserViewModel
import com.example.testapp.views.home.HomeView
import kotlinx.coroutines.launch
import kotlin.math.log

@Composable
fun AppNavigation(navController: NavHostController, userViewModel: UserViewModel, context: Context) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(errorMessage) {
        if (errorMessage.isNotEmpty()) {
            snackbarHostState.showSnackbar(errorMessage)
            errorMessage = ""
        }
    }
    SnackbarHost(hostState = snackbarHostState)

    NavHost(navController = navController, startDestination = Screen.SignIn.route) {
        composable(Screen.SignIn.route) {
            SignInView(navController) { emailInput, passwordInput ->
                email = emailInput
                password = passwordInput
                coroutineScope.launch {
                    userViewModel.login(email, password, context) { message ->
                        errorMessage = message
                    }
                }
            }
        }
        composable(Screen.SignUp.route) {
            SignUpView(navController) { emailInput, passwordInput, rePasswordInput, nameInput ->
                email = emailInput
                password = passwordInput
                name = nameInput
                coroutineScope.launch {
                    if (rePasswordInput == passwordInput) {
                        userViewModel.signup(email, password, name, context) { message ->
                            errorMessage = message
                        }
                    }else{
                        errorMessage = "Passwords are not identical."
                    }
                }
            }
        }
    }
}