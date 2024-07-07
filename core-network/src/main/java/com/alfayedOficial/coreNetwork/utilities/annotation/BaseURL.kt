package com.alfayedOficial.coreNetwork.utilities.annotation

import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class BaseURL(
    /** The name.  */
    val value: String = "baseURL"
)
