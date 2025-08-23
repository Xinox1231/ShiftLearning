package ru.mavrinvladislav.db.entity

import androidx.room.ColumnInfo

data class RegistrationDb(
    @ColumnInfo("registration_date") val date: String,
    val yearsSinceRegistered: Int
)