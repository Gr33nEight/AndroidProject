package com.example.testapp.views

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapp.R
import com.example.testapp.ui.theme.DarkPurple
import com.example.testapp.ui.theme.TestAppTheme
import com.example.testapp.utils.CustomTextField

@Composable
fun SignInView() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painterResource(R.drawable.logo),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(top = 70.dp)
                .size(129.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text("Sign in",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            color = DarkPurple,
        )

        CustomTextField(
            value = "Email or User Name",
            onValueChange = { email = it },
            modifier = Modifier
        )
        CustomTextField(
            value = "Password",
            onValueChange = { password = it },
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignInPreview() {
    TestAppTheme {
        SignInView()
    }
}
