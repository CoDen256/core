package io.github.coden256.absence

import io.github.coden256.absence.api.Absence
import net.fortuna.ical4j.data.CalendarBuilder
import net.fortuna.ical4j.model.Calendar
import net.fortuna.ical4j.model.component.VEvent
import java.io.InputStream
import java.net.URI
import java.time.ZonedDateTime

class ICSCalendar(private val url: String) : io.github.coden256.absence.api.Calendar {
    override fun absences(): List<Absence> {
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
                Absence(
                    it.summary.value,
                    it.getDateTimeStart<ZonedDateTime>().date.toLocalDateTime(),
                    it.getDateTimeEnd<ZonedDateTime>().date.toLocalDateTime()
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