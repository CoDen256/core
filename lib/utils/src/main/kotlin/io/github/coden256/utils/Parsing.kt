package io.github.coden256.utils

fun parsePrice(text: String?, unitFactor: Double? = null): Double? {
    if (text.isNullOrEmpty()) return null
    val factor = unitFactor ?: when {
        text.contains("[Cc]([Ee][Nn])?[Tt]\\.".toRegex()) -> 0.01
        else -> 1.0
    }

    val res = "\\d+.?\\d*".toRegex().find(text.replace(",", "."))?.value?.trim() ?: return null
    return res.toDoubleOrNull()?.let {
        it * factor
    }
}
