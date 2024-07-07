package com.alfayedOficial.androidDevelopmentTechnicalTask.core.utils

import android.content.Context
import android.widget.Toast

fun Context.kuToast(value :String){
    Toast.makeText(this, value, Toast.LENGTH_LONG).show()
}