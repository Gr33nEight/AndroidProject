package com.example.testapp.views.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapp.R
import com.example.testapp.models.Contact
import com.example.testapp.ui.theme.Pink

@Composable
fun ContactCardView(contact: Contact, startDeleteMode: Boolean, onButtonClick: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(size = 20.dp))
        .background(if (startDeleteMode) Color.Red else Pink)
        .padding(horizontal = 20.dp, vertical = 10.dp)
        .clickable {
            onButtonClick()
        }
        ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(contact.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(contact.phoneNumber,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Box(modifier = Modifier
                .clip(CircleShape)
                .background(Color.White)
                .padding(15.dp)
            ) {
                Icon(
                    modifier = Modifier,
                    painter = painterResource(
                        if (startDeleteMode) R.drawable.ic_delete else R.drawable.ic_phone
                    ),
                    contentDescription = ""
                )
            }
        }
    }
}