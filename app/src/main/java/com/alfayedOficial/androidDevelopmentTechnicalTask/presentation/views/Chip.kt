package com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.ClearBlue
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.Blue
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.BlueBetween
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.RoundedShape
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.spacing_2_2
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.spacing_4

@Composable
fun CategoryChip(category: String?, modifier: Modifier = Modifier) {


    Box(
        modifier = modifier
            .background(
                Brush.horizontalGradient(
                    colors = listOf(
                        Blue,
                        BlueBetween,
                        ClearBlue
                    ),
                    startX = 0f,
                    endX = Float.POSITIVE_INFINITY
                ), shape = RoundedShape
            )
            .wrapContentHeight()
            .wrapContentWidth()
    ) {
        Text(
            text = category.orEmpty(),
            style = MaterialTheme.typography.bodySmall,
            color = Color.White,
            modifier = Modifier.padding(
                horizontal = spacing_4,
                vertical = spacing_2_2
            )
        )
    }
}
