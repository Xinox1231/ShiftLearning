package ru.mavrinvladislav.user.domain.model

data class User(
    val gender: String,
    val name: Name,
    val location: Location,
    val email: String,
    val dateOfBirth: DateOfBirth,
    val registered: Registration,
    val phone: String,
    val cell: String,
    val picture: Picture,
    val nat: String
)