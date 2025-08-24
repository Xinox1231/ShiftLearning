package ru.mavrinvladislav.utils

import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun String.toDate(): String {
    val dateFormat = DateTimeFormatter.ofPattern(
        "dd.MM.yyyy"
    )
    val instant = java.time.Instant.parse(this)
    return instant.atOffset(ZoneOffset.UTC).toLocalDateTime().format(dateFormat)
}