package io.github.coden.absence

import io.github.coden.absence.api.AbsenceService
import io.github.coden.absence.api.OutOfOffice
import net.fortuna.ical4j.data.CalendarBuilder
import net.fortuna.ical4j.model.Calendar
import net.fortuna.ical4j.model.component.VEvent
import java.io.InputStream
import java.net.URI
import java.time.ZonedDateTime

class ICSAbsenceService(private val url: String) : AbsenceService {
    override fun getAbsences(): List<OutOfOffice> {
        val calendar = fetchCalendar()

        return calendar
            .getComponents<VEvent>()
            .filterIsInstance<VEvent>()
            .mapNotNull {
                try {
                    it.summary
                    it
                } catch (e: Exception) {
                    null
                }
            }.filter { it.summary.value.contains("Private") }
            .map {
                OutOfOffice(
                    it.summary.value,
                    it.getDateTimeStart<ZonedDateTime>().date.toLocalDate(),
                    it.getDateTimeEnd<ZonedDateTime>().date.toLocalDate()
                )
            }
    }

    private fun fetchCalendar(): Calendar {
        return CalendarBuilder().build(fetchICS())
    }

    private fun fetchICS(): InputStream {
        return URI(url).toURL().openStream()
    }

}