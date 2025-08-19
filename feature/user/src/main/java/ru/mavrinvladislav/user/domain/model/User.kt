package ru.mavrinvladislav.user.domain.model

data class User(
    val gender: Gender,
    val name: Name,
    val location: Location,
    val email: String,
    val dateOfBirth: String,
    val age: Int,
    val registered: Registration,
    val phone: String,
    val cell: String,
    val picture: Picture,
    val nat: String
)