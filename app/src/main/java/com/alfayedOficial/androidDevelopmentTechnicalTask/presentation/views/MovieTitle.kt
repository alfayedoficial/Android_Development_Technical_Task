package com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.views

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun MovieTitle(
    title: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
    fontSize : TextUnit = 14.sp,
    style: TextStyle = TextStyle.Default.copy(fontSize = fontSize)
) {
    Text(
        text = title,
        style = style,
        color = Color.White,
        textAlign = textAlign,
        modifier = modifier
    )
}
