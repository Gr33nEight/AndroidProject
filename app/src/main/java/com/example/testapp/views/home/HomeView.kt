package com.example.testapp.views.home

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.net.Uri
import android.util.Log
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.testapp.R
import com.example.testapp.models.contact.Contact
import com.example.testapp.ui.theme.LightPurple
import com.example.testapp.ui.theme.Pink
import com.example.testapp.utils.Mode
import com.example.testapp.view_models.ContactViewModel
import com.example.testapp.view_models.UserViewModel

@Composable
fun HomeView(contactViewModel: ContactViewModel = viewModel(),context: Context, logout: () -> Unit) {

    var pickedContact by remember { mutableStateOf<Contact?>(null) }

    val contacts by contactViewModel.contacts.collectAsState()
    var mode by remember { mutableStateOf(Mode.NONE) }
    var isSheetOpen by remember { mutableStateOf(false) }

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Text("Contact List", fontWeight = FontWeight.Bold)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        painterResource(R.drawable.ic_logout),
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            logout()
                        }
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                        Icon(
                            painterResource(R.drawable.ic_edit),
                            contentDescription = "",
                            modifier = Modifier.clickable {
                                mode = if (mode == Mode.NONE) Mode.EDIT else Mode.NONE
                            }
                        )
                        Icon(
                            painterResource(R.drawable.ic_delete),
                            contentDescription = "",
                            modifier = Modifier.clickable {
                                mode = if (mode == Mode.NONE) Mode.DELETE else Mode.NONE
                            }
                        )
                    }
                }
            }
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(contacts) { contact ->
                    ContactCardView(contact, mode) {
                        when (mode) {
                            Mode.NONE -> {
                                val intent = Intent(Intent.ACTION_DIAL).apply {
                                    data = Uri.parse("tel:${contact.phoneNumber}")
                                }
                                context.startActivity(intent)
                            }
                            Mode.EDIT -> {
                                isSheetOpen = true
                                pickedContact = contact
                            }
                            Mode.DELETE -> {
                                contactViewModel.deleteContact(contact)
                            }
                        }
                        mode = Mode.NONE
                    }
                }
            }
        }
        IconButton({
            pickedContact = null
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
        AddOrUpdateContactView(pickedContact) { name, phoneNumber ->
            name?.let { unwrappedName ->
                phoneNumber?.let { unwrappedPhoneNumber ->
                    if (phoneNumber.length == 9 && name.length > 2) {
                        if (pickedContact == null) {
                            contactViewModel.addContact(unwrappedName, unwrappedPhoneNumber)
                        }else{
                            val contact = Contact(
                                id = pickedContact!!.id,
                                name = unwrappedName,
                                phoneNumber = unwrappedPhoneNumber
                            )
                            contactViewModel.updateContact(contact)
                        }
                    }
                }
            }
            isSheetOpen = false
        }
    }
}

