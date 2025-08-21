package ru.mavrinvladislav.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("users")
data class UserDb(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val gender: GenderDb,
    @Embedded val name: NameDb,
    @Embedded val location: LocationDb,
    val email: String,
    val dateOfBirth: String,
    val age: Int,
    @Embedded val registered: RegistrationDb,
    val phone: String,
    val cell: String,
    @Embedded val picture: PictureDb,
    val nat: String
)