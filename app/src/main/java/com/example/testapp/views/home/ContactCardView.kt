package com.example.testapp.views.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapp.R
import com.example.testapp.models.contact.Contact
import com.example.testapp.ui.theme.Pink
import com.example.testapp.utils.Mode

@Composable
fun ContactCardView(contact: Contact, mode: Mode, onButtonClick: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(size = 20.dp))
        .border(3.dp, mode.modeColor(), RoundedCornerShape(20.dp))
        .background(Color.LightGray.copy(alpha = 0.5f))
        .padding(horizontal = 20.dp, vertical = 20.dp)
        .clickable(
            interactionSource = null,
            indication = null,
            onClick = onButtonClick
        )
        ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(contact.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = mode.modeColor(),
            modifier = Modifier.fillMaxWidth(0.5f)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Text(contact.phoneNumber,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                style = TextStyle(
                    textDecoration = TextDecoration.Underline,
                    color = mode.modeColor()
                )
            )
            Box(modifier = Modifier
                .clip(CircleShape)
                .background(mode.modeColor())
                .padding(15.dp)
            ) {
                Icon(
                    modifier = Modifier,
                    painter = painterResource(mode.iconColor()),
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }
    }
}