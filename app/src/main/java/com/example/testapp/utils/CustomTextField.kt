package com.example.testapp.utils

import android.graphics.drawable.Icon
import android.hardware.lights.Light
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.testapp.ui.theme.DarkPurple
import com.example.testapp.ui.theme.LightPurple
import com.example.testapp.ui.theme.TestAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: Painter? = null,
    onTrailingIconClick: () -> Unit = {},
    trailingIcon: Painter? = null,
    visualTransformation: VisualTransformation? = null
) {

    val customTextFieldColors = TextFieldDefaults.colors(
        focusedIndicatorColor = DarkPurple,
        unfocusedIndicatorColor = DarkPurple,
        focusedLabelColor = DarkPurple,
        unfocusedLabelColor = Color.Gray
    )

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        colors = customTextFieldColors,
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        // icons
        leadingIcon = {
            if (leadingIcon != null)
                Icon(
                    modifier = Modifier,
                    painter = leadingIcon, contentDescription = null
                )
        },
        trailingIcon = {
            if (trailingIcon != null)
                IconButton(onClick = onTrailingIconClick) {
                    Icon(
                        modifier = Modifier,
                        painter = trailingIcon, contentDescription = null
                    )
                }
        },
        visualTransformation = if(visualTransformation != null) {
            visualTransformation
        } else {
            VisualTransformation.None
        }
    )
}
