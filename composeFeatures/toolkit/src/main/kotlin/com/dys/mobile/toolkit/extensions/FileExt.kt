package com.dys.mobile.toolkit.extensions

fun String.toFileType(): FileType {
    val lower = this.lowercase()
    return when {
        lower.endsWith(".pdf") -> FileType.PDF
        lower.matches(Regex(".*\\.(png|jpg|jpeg)$")) -> FileType.IMAGE
        else -> FileType.UNKNOWN
    }
}

enum class FileType {
    IMAGE, PDF, UNKNOWN
}