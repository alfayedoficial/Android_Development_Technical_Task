package com.alfayedOficial.androidDevelopmentTechnicalTask.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.navigation.AppLauncherNavigation
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.TMDbMoviesTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMDbMoviesTaskTheme {
                AppLauncherNavigation()
            }
        }
    }
}

