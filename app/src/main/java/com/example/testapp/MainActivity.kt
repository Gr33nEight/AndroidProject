package com.example.testapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.testapp.models.AppDatabase
import com.example.testapp.models.ContactRepository
import com.example.testapp.ui.theme.TestAppTheme
import com.example.testapp.view_models.ContactViewModel
import com.example.testapp.views.AppNavigation
import com.example.testapp.views.SignInView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(this)
        val repository = ContactRepository(database.contactDao())
        val viewModel = ViewModelProvider(this, ViewModelFactory(repository))[ContactViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            TestAppTheme {
                AppNavigation(navController, viewModel)
            }
        }
    }
}


class ViewModelFactory(private val repository: ContactRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ContactViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}