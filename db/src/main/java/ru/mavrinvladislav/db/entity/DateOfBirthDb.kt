package ru.mavrinvladislav.db.entity

import androidx.room.ColumnInfo

data class DateOfBirthDb(
    @ColumnInfo("date_of_birth") val date: String,
    val age: String
)