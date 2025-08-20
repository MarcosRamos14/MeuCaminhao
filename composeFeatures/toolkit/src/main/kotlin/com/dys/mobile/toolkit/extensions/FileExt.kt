package com.dys.mobile.toolkit.extensions

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.util.UUID

fun String.toFileType(): FileType {
    val lower = this.lowercase()
    return when {
        lower.endsWith(".pdf") -> FileType.PDF
        lower.matches(Regex(".*\\.(png|jpg|jpeg)$")) -> FileType.IMAGE
        else -> FileType.UNKNOWN
    }
}

fun Bitmap.toUri(context: Context): Uri {
    val file = File(context.cacheDir, "photo_${UUID.randomUUID()}.jpg")
    FileOutputStream(file).use { out ->
        compress(Bitmap.CompressFormat.JPEG, 100, out)
    }
    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileProvider",
        file
    )
}

enum class FileType {
    IMAGE, PDF, UNKNOWN
}