package com.example.testapp.view_models

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserViewModel: ViewModel() {
    private var auth = FirebaseAuth.getInstance()
    private var firestore = FirebaseFirestore.getInstance()

    private val _isUserLoggedIn = MutableLiveData<Boolean>()
    val isUserLoggedIn: LiveData<Boolean> get() = _isUserLoggedIn

    init {
        _isUserLoggedIn.value = false
    }

    suspend fun login(email: String, password: String, context: Context, onErrorAction: (message: String) -> Unit) {
        try {
            val authResult: AuthResult = auth.signInWithEmailAndPassword(email, password).await()
            val user = authResult.user
            if (user != null) {
                saveUserCredentials(context, true)
            }
        } catch (e: Exception) {
            onErrorAction("Incorrect username or password. Please try again.")
        }
    }

    suspend fun signup(email: String, password: String, name: String, context: Context, onErrorAction: (message: String) -> Unit) {
        try {
            val authResult: AuthResult = auth.createUserWithEmailAndPassword(email, password).await()
            val user = authResult.user
            user?.let {
                addUserToDatabase(user, name)
                saveUserCredentials(context, true)
            }
        } catch (e: Exception) {
            onErrorAction("Incorrect data. Check the entered information and try again.")
        }
    }

    private suspend fun addUserToDatabase(user: FirebaseUser, name: String) {
        val userMap = mapOf(
            "name" to name,
            "email" to user.email
        )

        try {
            firestore.collection("users")
                .document(user.uid)
                .set(userMap)
                .await()
        } catch (e: Exception) {
            Log.e("AuthViewModel", "Error saving user to Firestore: ${e.message}")
        }
    }

    fun logout(context: Context) {
        auth.signOut()
        saveUserCredentials(context, false)
    }

    private fun saveUserCredentials(context: Context, isLoggedIn: Boolean) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putBoolean("isLoggedIn", isLoggedIn)
        editor.apply()
        checkLoginStatus(context)
    }

    fun checkLoginStatus(context: Context) {
        _isUserLoggedIn.value = getLoginStatusFromPreferences(context)
    }

    private fun getLoginStatusFromPreferences(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }
}