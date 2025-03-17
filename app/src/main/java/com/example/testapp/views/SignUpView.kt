package com.example.testapp.views

import android.graphics.Point
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SegmentedButtonDefaults.Icon
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
import androidx.navigation.compose.rememberNavController
import com.example.testapp.R
import com.example.testapp.ui.theme.DarkPurple
import com.example.testapp.ui.theme.Pink
import com.example.testapp.ui.theme.TestAppTheme
import com.example.testapp.utils.CustomPasswordField
import com.example.testapp.utils.CustomTextField
import com.example.testapp.utils.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpView(navController: NavController, signUp: (String, String, String, String) -> Unit) {
    val textStyle = TextStyle(
        color = DarkPurple
    )

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rePassword by remember { mutableStateOf("") }
    var hidePassword by remember { mutableStateOf(true) }
    var hideRePassword by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .clickable {
                        navController.popBackStack()
                }
            ) {
                androidx.compose.material3.Icon(
                    modifier = Modifier,
                    painter = painterResource(R.drawable.ic_chevron), contentDescription = null
                )
                Text("Back")
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Text("Sign Up",
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                color = DarkPurple,
            )

            CustomTextField(
                value = fullName,
                onValueChange = { fullName = it },
                modifier = Modifier,
                label = "Full Name",
                leadingIcon = painterResource(R.drawable.ic_person)
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
            CustomPasswordField(
                password = rePassword,
                onPasswordChange = { rePassword = it },
                onTrailingIconClick = { hideRePassword = !hideRePassword },
                hidePassword = hideRePassword,
            )
        }
        Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Button( {
                signUp(email, password, rePassword, fullName)
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
                Text("Sign Up")
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterHorizontally),
            modifier = Modifier.fillMaxWidth().padding(bottom = 50.dp)
        ) {
            Text("Already have an account?", style = textStyle)
            TextButton({
                navController.popBackStack()
            }) {
                Text("Sign In", style = textStyle, fontWeight = FontWeight.Bold

                )
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun SignInPreview() {
//    TestAppTheme {
//        SignUpView(navController = rememberNavController())
//    }
//}
