package com.alfayedOficial.coreNetwork.utilities.annotation

import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class ImageBaseURL(
    /** The name.  */
    val value: String = "imageBaseURL"
)
