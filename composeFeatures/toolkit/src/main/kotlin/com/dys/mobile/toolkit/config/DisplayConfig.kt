package com.dys.mobile.toolkit.config

import android.content.Context

object DisplayConfig {

    var density = 1f
    var fontDensity = 1f
    var widthPixels = 1
    var heightPixels = 1

    fun onConfigChanged(context: Context) {
        val resources = context.resources
        val metrics = resources.displayMetrics
        val configuration = resources.configuration

        density = metrics.density
        widthPixels = metrics.widthPixels
        heightPixels = metrics.heightPixels
        fontDensity = configuration.fontScale * metrics.density
    }
}