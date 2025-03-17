package com.example.testapp.views.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapp.R
import com.example.testapp.models.contact.Contact
import com.example.testapp.ui.theme.Pink
import com.example.testapp.utils.CustomTextField
import com.example.testapp.utils.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddOrUpdateContactView(contact: Contact?, onDismiss: (String?, String?) -> Unit) {
    val modalSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    var name by remember { mutableStateOf<String?>(null) }
    var phoneNumber by remember { mutableStateOf<String?>(null) }

    ModalBottomSheet(
        onDismissRequest = { onDismiss(name, phoneNumber) },
        sheetState = modalSheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Text("Add new contact", fontWeight = FontWeight.Bold, fontSize = 30.sp)
            Text("Phone number should have 9 digits and name should have more than 2 letters ",
                fontWeight = FontWeight.Light,
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
            CustomTextField(
                value = name ?: "",
                onValueChange = { newValue ->
                    name = newValue
                },
                label = "Name",
                leadingIcon = painterResource(R.drawable.ic_person)
            )
            CustomTextField(
                value = phoneNumber ?: "",
                onValueChange = { newValue ->
                    phoneNumber = newValue
                },
                label = "Phone number",
                leadingIcon = painterResource(R.drawable.ic_phone),
                keyboardType = KeyboardType.Phone
            )

            if (name != null && phoneNumber != null) {
                if (phoneNumber?.length!! == 9 && name?.length!! > 2) {
                    Button(
                        {
                            onDismiss(name, phoneNumber)
                        },
                        colors = ButtonColors(
                            contentColor = Color.White,
                            containerColor = Pink,
                            disabledContentColor = Color.LightGray,
                            disabledContainerColor = Color.Gray
                        ),
                        shape = RoundedCornerShape(18.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text("Add")
                    }
                }
            }
        }
    }

    contact?.let {
        LaunchedEffect(it) {
            name = it.name
            phoneNumber = it.phoneNumber
        }
    }
}