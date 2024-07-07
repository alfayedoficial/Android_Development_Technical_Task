package com.alfayedOficial.androidDevelopmentTechnicalTask

import android.app.Application
import coil.Coil
import coil.ImageLoader
import coil.request.CachePolicy
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application:Application(){

    override fun onCreate() {
        super.onCreate()

        val imageLoader = ImageLoader.Builder(this)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .build()

        Coil.setImageLoader(imageLoader) // set Coil as the default loader
    }
}