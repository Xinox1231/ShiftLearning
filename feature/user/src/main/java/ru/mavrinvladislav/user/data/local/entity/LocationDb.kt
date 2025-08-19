package ru.mavrinvladislav.user.data.local.entity

data class LocationDb(
    val street: StreetDb,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: CoordinatesDb,
    val timezone: TimezoneDb
)
