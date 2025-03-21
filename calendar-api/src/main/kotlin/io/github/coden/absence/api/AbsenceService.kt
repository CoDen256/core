package io.github.coden256.absence.api

import java.time.LocalDate

data class OutOfOffice(val summary: String, val start: LocalDate, val end: LocalDate)

interface AbsenceService {
    fun getAbsences(): List<OutOfOffice>
}
