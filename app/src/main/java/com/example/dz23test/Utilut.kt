package com.example.dz23test

import android.view.View
import androidx.core.view.isVisible

fun View.visibilityOn() {
    this.isVisible = true
}
fun View.visibilityOff() {
    this.isVisible = false
}