package io.github.coden256.calendar.api

import java.time.LocalDateTime

data class Absence(val summary: String, val start: LocalDateTime, val end: LocalDateTime)

interface Calendar {
    fun absences(): List<Absence>
}
