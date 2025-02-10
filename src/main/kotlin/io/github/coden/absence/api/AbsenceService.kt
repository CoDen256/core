package io.github.coden.absence.api

import java.time.LocalDate

data class OutOfOffice(val summary: String, val start: LocalDate, val end: LocalDate)

interface AbsenceService {
    fun getAbsences(): List<OutOfOffice>
}
