package com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.features.splash.ui

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.MainColor
import com.alfayedoficial.androiddevelopmenttechnicaltask.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onMoveActionRoute: () -> Unit ={}) {
    val scale = remember { Animatable(0f) }

    SplashScreenEffect(onMoveActionRoute = onMoveActionRoute, scale,)

    SplashScreenUI(scale)
}

@Composable
fun SplashScreenEffect(onMoveActionRoute: () -> Unit, scale: Animatable<Float, AnimationVector1D>) {

    LaunchedEffect(key1 = true) {
        scale.animateTo(targetValue = 0.9f, animationSpec = tween(durationMillis = 500, easing = {
            OvershootInterpolator(2f).getInterpolation(it)
        }))

        delay(3000L)
        onMoveActionRoute()
    }
}

@Composable
fun SplashScreenUI(scale: Animatable<Float, AnimationVector1D>) {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize().background(MainColor)
    ) {
        SplashScreenImage(scale)
    }
}

@Composable
fun SplashScreenImage(scale: Animatable<Float, AnimationVector1D>) {
    Image(
        painter = painterResource(id = R.drawable.ic_logo),
        contentDescription = "Logo",
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .scale(scale.value)
    )
}


@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}

