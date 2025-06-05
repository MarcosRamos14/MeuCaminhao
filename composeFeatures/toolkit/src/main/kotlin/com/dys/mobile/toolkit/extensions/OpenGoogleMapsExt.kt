package com.dys.mobile.toolkit.extensions

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

fun Context.openGoogleMaps(
    originLatitude: Double,
    originLongitude: Double,
    destinationLatitude: Double,
    destinationLongitude: Double
) {
    val uri = "https://www.google.com/maps/dir/".toUri()
        .buildUpon()
        .appendQueryParameter("api", "1")
        .appendQueryParameter("origin", "$originLatitude,$originLongitude")
        .appendQueryParameter("destination", "$destinationLatitude,$destinationLongitude")
        .build()

    val mapIntent = Intent(Intent.ACTION_VIEW, uri).apply {
        setPackage("com.google.android.apps.maps")
    }

    val fallbackUri = "https://maps.google.com/maps".toUri()
        .buildUpon()
        .appendQueryParameter("saddr", "$originLatitude,$originLongitude")
        .appendQueryParameter("daddr", "$destinationLatitude,$destinationLongitude")
        .build()

    val fallbackIntent = Intent(Intent.ACTION_VIEW, fallbackUri)

    runCatching {
        startActivity(mapIntent)
    }.onFailure {
        startActivity(Intent.createChooser(fallbackIntent, "Escolha um app de mapas"))
    }
}