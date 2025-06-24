package com.dys.mobile.toolkit.extensions

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.net.toUri

fun Context.openPdfExternally(pdfUrl: String) {
    try {
        val uri = pdfUrl.toUri()

        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, "application/pdf")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        val chooser = Intent.createChooser(intent, "Abrir PDF com")
        startActivity(chooser)
    } catch (e: Exception) {
        e.printStackTrace()
        Toast.makeText(this, "Não foi possível abrir o PDF", Toast.LENGTH_SHORT).show()
        // TODO: Mostar toast personalizada. Implementação em outra brach
    }
}