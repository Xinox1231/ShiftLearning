package ru.mavrinvladislav.user.data.local.entity

data class UserDb(
    val gender: GenderDb,
    val name: NameDb,
    val location: LocationDb,
    val email: String,
    val dateOfBirth: String,
    val age: Int,
    val registered: RegistrationDb,
    val phone: String,
    val cell: String,
    val picture: PictureDb,
    val nat: String
)