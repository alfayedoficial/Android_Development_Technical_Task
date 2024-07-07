package com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.features.ticket

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.MainColor100
import com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.navigation.ScreenItem

/**
 * Created by [Ali Al Fayed](https://www.linkedin.com/in/alfayedoficial)
 * About class :
 * Date Created: 06/07/2024 - 5:05 PM
 * Last Modified: 06/07/2024 - 5:05 PM
 */


@Composable
fun TicketScreen(modifier: Modifier = Modifier) {

    Box(contentAlignment = Alignment.Center, modifier = modifier) {

        Text("Screen under development", modifier = modifier , style = TextStyle(
            fontSize = 20.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            color = MainColor100
        ))
    }
}
