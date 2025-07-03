package com.dys.mobile.toolkit.extensions

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

fun Context.openGoogleMaps(
    originLat: Double,
    originLng: Double,
    destinationLat: Double,
    destinationLng: Double
) {
    val mapsUri = "https://www.google.com/maps/dir/".toUri()
        .buildUpon()
        .appendQueryParameter("api", "1")
        .appendQueryParameter("origin", "$originLat,$originLng")
        .appendQueryParameter("destination", "$destinationLat,$destinationLng")
        .build()

    val intent = Intent(Intent.ACTION_VIEW, mapsUri)

    startActivity(intent)
}