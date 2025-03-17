package com.example.testapp.views

import android.graphics.Point
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.testapp.R
import com.example.testapp.ui.theme.DarkPurple
import com.example.testapp.ui.theme.Pink
import com.example.testapp.ui.theme.TestAppTheme
import com.example.testapp.utils.CustomPasswordField
import com.example.testapp.utils.CustomTextField
import com.example.testapp.utils.Screen
import kotlin.math.sign
import kotlin.math.sin

@Composable
fun SignInView(navController: NavController, signIn: (String, String) -> Unit) {
    val textStyle = TextStyle(
        color = DarkPurple
    )

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var hidePassword by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween
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
        Column(
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Text("Sign in",
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                color = DarkPurple,
            )

            CustomTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier,
                label = "Email",
                leadingIcon = painterResource(R.drawable.ic_person)
            )
            CustomPasswordField(
                password = password,
                onPasswordChange = { password = it },
                onTrailingIconClick = { hidePassword = !hidePassword },
                hidePassword = hidePassword,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton({}) {
                    Text("Forget Password?", style = textStyle, fontWeight = FontWeight.Bold)
                }
            }
        }
        Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Button( {
                signIn(email, password)
            },
                colors = ButtonColors(
                    contentColor = Color.White,
                    containerColor = Pink,
                    disabledContentColor = Color.LightGray,
                    disabledContainerColor = Color.Gray
                ),
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("Sign in")
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterHorizontally),
            modifier = Modifier.fillMaxWidth().padding(bottom = 50.dp)
        ) {
            Text("Don't have account?", style = textStyle)
            TextButton({
                navController.navigate(Screen.SignUp.route)
            }) {
                Text("Sign Up", style = textStyle, fontWeight = FontWeight.Bold

                )
            }
        }
    }
}
