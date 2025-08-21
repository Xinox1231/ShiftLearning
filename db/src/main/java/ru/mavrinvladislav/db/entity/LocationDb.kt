package ru.mavrinvladislav.db.entity

import androidx.room.Embedded

data class LocationDb(
    @Embedded val street: StreetDb,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    @Embedded val coordinates: CoordinatesDb,
    @Embedded val timezone: TimezoneDb
)
