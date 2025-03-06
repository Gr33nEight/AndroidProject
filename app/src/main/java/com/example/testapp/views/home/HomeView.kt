package com.example.testapp.views.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.testapp.R
import com.example.testapp.ui.theme.Pink
import com.example.testapp.models.Contact
import com.example.testapp.ui.theme.LightPurple
import com.example.testapp.view_models.ContactViewModel

@Composable
fun HomeView(contactViewModel: ContactViewModel = viewModel(), navController: NavController) {

    val contacts by contactViewModel.contacts.collectAsState()
    var startDeleteMode by remember { mutableStateOf(false) }
    var isSheetOpen by remember { mutableStateOf(false) }

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painterResource(R.drawable.ic_logout),
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    }
                )
                Text("Contact List", fontWeight = FontWeight.Bold)
                Icon(
                    painterResource(R.drawable.ic_delete),
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        startDeleteMode = !startDeleteMode
                    }
                )
            }
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(contacts) { contact ->
                    ContactCardView(contact, startDeleteMode) {
                        if (startDeleteMode) {
                            contactViewModel.deleteContact(contact)
                            startDeleteMode = false
                        }else{
                            // call number
                        }
                    }
                }
            }
        }
        IconButton({
            isSheetOpen = true
        }, modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(bottom = 50.dp, end = 20.dp)
            .clip(CircleShape)
            .background(LightPurple)
            .padding(5.dp)
        ) {
            Icon(
                painterResource(R.drawable.ic_plus),
                contentDescription = "",
                tint = Color.White,
            )
        }
    }
    if (isSheetOpen) {
        AddContactView { name, phoneNumber ->
            name?.let { unwrappedName ->
                phoneNumber?.let { unwrappedPhoneNumber ->
                    if (phoneNumber.length == 9 && name.length > 2) {
                        contactViewModel.addContact(unwrappedName, unwrappedPhoneNumber)
                    }
                }
            }
            isSheetOpen = false
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun HomePreview() {
//    TestAppTheme {
//        val navController = rememberNavController()
//
//        HomeView(navController = navController)
//    }
//}
//
