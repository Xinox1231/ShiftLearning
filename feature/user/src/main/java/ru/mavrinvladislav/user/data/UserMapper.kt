package ru.mavrinvladislav.user.data

import ru.mavrinvladislav.db.entity.CoordinatesDb
import ru.mavrinvladislav.db.entity.DateOfBirthDb
import ru.mavrinvladislav.db.entity.LocationDb
import ru.mavrinvladislav.db.entity.NameDb
import ru.mavrinvladislav.db.entity.PictureDb
import ru.mavrinvladislav.db.entity.RegistrationDb
import ru.mavrinvladislav.db.entity.StreetDb
import ru.mavrinvladislav.db.entity.TimezoneDb
import ru.mavrinvladislav.db.entity.UserDb
import ru.mavrinvladislav.user.data.remote.model.CoordinatesDto
import ru.mavrinvladislav.user.data.remote.model.DateOfBirthDto
import ru.mavrinvladislav.user.data.remote.model.LocationDto
import ru.mavrinvladislav.user.data.remote.model.NameDto
import ru.mavrinvladislav.user.data.remote.model.PictureDto
import ru.mavrinvladislav.user.data.remote.model.RegistrationDto
import ru.mavrinvladislav.user.data.remote.model.StreetDto
import ru.mavrinvladislav.user.data.remote.model.TimezoneDto
import ru.mavrinvladislav.user.data.remote.model.UserDto
import ru.mavrinvladislav.user.domain.model.Coordinates
import ru.mavrinvladislav.user.domain.model.DateOfBirth
import ru.mavrinvladislav.user.domain.model.Location
import ru.mavrinvladislav.user.domain.model.Name
import ru.mavrinvladislav.user.domain.model.Picture
import ru.mavrinvladislav.user.domain.model.Registration
import ru.mavrinvladislav.user.domain.model.Street
import ru.mavrinvladislav.user.domain.model.Timezone
import ru.mavrinvladislav.user.domain.model.User

internal fun UserDto.toEntity(): UserDb =
    UserDb(
        gender = gender,
        name = name.toEntity(),
        location = location.toEntity(),
        email = email,
        dateOfBirth = dateOfBirth.toEntity(),
        registered = registered.toEntity(),
        phone = phone,
        cell = cell,
        picture = picture.toEntity(),
        nat = nat
    )

internal fun NameDto.toEntity(): NameDb =
    NameDb(
        title = title,
        first = first,
        last = last
    )

internal fun StreetDto.toEntity(): StreetDb =
    StreetDb(
        number = number,
        name = name
    )

internal fun CoordinatesDto.toEntity(): CoordinatesDb =
    CoordinatesDb(
        latitude = latitude,
        longitude = longitude
    )

internal fun TimezoneDto.toEntity(): TimezoneDb =
    TimezoneDb(
        offset = offset,
        description = description
    )

internal fun LocationDto.toEntity(): LocationDb =
    LocationDb(
        street = street.toEntity(),
        city = city,
        state = state,
        country = country,
        postcode = postcode,
        coordinates = coordinates.toEntity(),
        timezone = timezone.toEntity()
    )

internal fun DateOfBirthDto.toEntity(): DateOfBirthDb =
    DateOfBirthDb(
        date = date,
        age = age
    )

internal fun RegistrationDto.toEntity(): RegistrationDb =
    RegistrationDb(
        date = date,
        yearsSinceRegistered = yearsSinceRegistered
    )

internal fun PictureDto.toEntity(): PictureDb =
    PictureDb(
        large = large,
        medium = medium,
        thumbnail = thumbnail
    )

internal fun UserDb.toDomain(): User =
    User(
        id = id,
        gender = gender,
        name = name.toDomain(),
        location = location.toDomain(),
        email = email,
        dateOfBirth = dateOfBirth.toDomain(),
        registered = registered.toDomain(),
        phone = phone,
        cell = cell,
        picture = picture.toDomain(),
        nat = nat
    )

internal fun NameDb.toDomain(): Name =
    Name(
        title = title,
        first = first,
        last = last
    )

internal fun StreetDb.toDomain(): Street =
    Street(
        number = number,
        name = name
    )

internal fun CoordinatesDb.toDomain(): Coordinates =
    Coordinates(
        latitude = latitude,
        longitude = longitude
    )

internal fun TimezoneDb.toDomain(): Timezone =
    Timezone(
        offset = offset,
        description = description
    )

internal fun LocationDb.toDomain(): Location =
    Location(
        street = street.toDomain(),
        city = city,
        state = state,
        country = country,
        postcode = postcode,
        coordinates = coordinates.toDomain(),
        timezone = timezone.toDomain()
    )

internal fun DateOfBirthDb.toDomain(): DateOfBirth =
    DateOfBirth(
        date = date,
        age = age
    )

internal fun RegistrationDb.toDomain(): Registration =
    Registration(
        date = date,
        yearsSinceRegistered = yearsSinceRegistered
    )

internal fun PictureDb.toDomain(): Picture =
    Picture(
        large = large,
        medium = medium,
        thumbnail = thumbnail
    )