package ru.mavrinvladislav.user.ui.mapper

import ru.mavrinvladislav.user.domain.model.User
import ru.mavrinvladislav.user.ui.model.UserUIModel
import ru.mavrinvladislav.utils.toDate

internal fun User.toUIModel(): UserUIModel {
    return UserUIModel(
        fullName = "${name.first} ${name.last}",
        coordinates = "${location.coordinates.latitude}, ${location.coordinates.longitude}",
        picture = picture.large,
        email = email.trim(),
        cell = cell,
        phone = phone,
        nationality = nat,
        dateOfBirthAndAge = "${dateOfBirth.date.toDate()}, ${dateOfBirth.age}",
        address = "${location.street.name.trim()}, ${location.city.trim()}, ${location.state.trim()}, ${location.country.trim()}"
    )
}