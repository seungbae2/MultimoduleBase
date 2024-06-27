package sb.multimodulebase.feature.news.util

import kotlinx.datetime.Instant
import kotlinx.datetime.toJavaInstant
import java.time.LocalDateTime
import java.time.ZoneId.systemDefault
import java.time.format.DateTimeFormatter

fun Instant.formatToDateTimeString(): String {
    val javaInstant = this.toJavaInstant()
    val timeZone = systemDefault()
    val today = LocalDateTime.now()
    val yesterday = today.minusDays(1)
    val inputDate = javaInstant.atZone(timeZone).toLocalDate()

    return if (today.toLocalDate() == inputDate) {
        val formatter = DateTimeFormatter.ofPattern("a hh:mm")
            .withZone(timeZone)
        formatter.format(javaInstant)
    } else {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            .withZone(timeZone)
        formatter.format(javaInstant)
    }
}