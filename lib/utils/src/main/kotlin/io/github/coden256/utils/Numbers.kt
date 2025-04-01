package io.github.coden256.utils

import java.math.RoundingMode

fun Double.roundTo(n: Int, roundingMode: RoundingMode = RoundingMode.HALF_EVEN): Double {
    return toBigDecimal().setScale(n, roundingMode).toDouble()
}

operator fun Double?.minus(other: Double?): Double? {
    if (this == null || other == null) return null
    return this - other
}

operator fun Double?.plus(other: Double?): Double? {
    if (this == null || other == null) return null
    return this + other
}