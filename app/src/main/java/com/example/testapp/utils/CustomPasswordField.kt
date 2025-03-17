package com.example.testapp.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.testapp.R

@Composable
fun CustomPasswordField(
    password: String,
    onPasswordChange: (String) -> Unit,
    onTrailingIconClick: () -> Unit,
    hidePassword: Boolean,
) {
    val trailingIcon =
        if (hidePassword) painterResource(R.drawable.ic_eye_closed)
        else painterResource(R.drawable.ic_eye_opened)

    val visualTransformation =
        if (hidePassword) PasswordVisualTransformation()
        else VisualTransformation.None

    CustomTextField(
        modifier = Modifier.fillMaxWidth(),
        value = password,
        onValueChange = onPasswordChange,
        leadingIcon = painterResource(R.drawable.ic_lock),
        trailingIcon = trailingIcon,
        onTrailingIconClick = onTrailingIconClick,
        label = "Forget Password?",
        visualTransformation = visualTransformation,
        keyboardType = KeyboardType.Password
    )
}