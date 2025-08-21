package ru.mavrinvladislav.user.data

import ru.mavrinvladislav.db.entity.CoordinatesDb
import ru.mavrinvladislav.db.entity.NameDb
import ru.mavrinvladislav.db.entity.PictureDb
import ru.mavrinvladislav.db.entity.RegistrationDb
import ru.mavrinvladislav.db.entity.StreetDb
import ru.mavrinvladislav.db.entity.TimezoneDb
import ru.mavrinvladislav.db.entity.UserDb
import ru.mavrinvladislav.user.domain.model.Coordinates
import ru.mavrinvladislav.user.domain.model.Location
import ru.mavrinvladislav.user.domain.model.Name
import ru.mavrinvladislav.user.domain.model.Picture
import ru.mavrinvladislav.user.domain.model.Registration
import ru.mavrinvladislav.user.domain.model.Street
import ru.mavrinvladislav.user.domain.model.Timezone
import ru.mavrinvladislav.user.domain.model.User

fun UserDb.toDomain(): User = User(
    gender = gender.toDomain(),
    name = name.toDomain(),
    location = location.toDomain(),
    email = email,
    dateOfBirth = dateOfBirth,
    age = age,
    login = login.toDomain(),
    dob = dob.toDomain(),
    registered = registered.toDomain(),
    phone = phone,
    cell = cell,
    id = id.toDomain(),
    picture = picture.toDomain(),
    nat = nat
)


fun NameDb.toDomain(): Name = Name(
    title = title,
    first = first,
    last = last
)

fun Location.toDomain(): Location = Location(
    street = street.toDomain(),
    city = city,
    state = state,
    country = country,
    postcode = postcode,
    coordinates = coordinates.toDomain(),
    timezone = timezone.toDomain()
)

fun StreetDb.toDomain(): Street = Street(
    number = number,
    name = name
)

fun CoordinatesDb.toDomain(): Coordinates = Coordinates(
    latitude = latitude,
    longitude = longitude
)

fun TimezoneDb.toDomain(): Timezone = Timezone(
    offset = offset,
    description = description
)

fun RegistrationDb.toDomain(): Registration = Registration(
    date = date,
    age = age
)

fun PictureDb.toDomain(): Picture = Picture(
    large = large,
    medium = medium,
    thumbnail = thumbnail
)