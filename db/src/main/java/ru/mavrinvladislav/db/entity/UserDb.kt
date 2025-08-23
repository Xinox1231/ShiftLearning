package ru.mavrinvladislav.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("users")
data class UserDb(
    @PrimaryKey(autoGenerate = true) val id: Long = UNKNOWN_ID,
    val gender: String,
    @Embedded val name: NameDb,
    @Embedded val location: LocationDb,
    val email: String,
    @Embedded val dateOfBirth: DateOfBirthDb,
    @Embedded val registered: RegistrationDb,
    val phone: String,
    val cell: String,
    @Embedded val picture: PictureDb,
    val nat: String
)

private const val UNKNOWN_ID = 0L