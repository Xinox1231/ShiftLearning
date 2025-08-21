package ru.mavrinvladislav.db.type_converter

import androidx.room.TypeConverter
import ru.mavrinvladislav.db.entity.GenderDb

class Converters {
    @TypeConverter
    fun fromGenderDb(gender: GenderDb?): String? {
        return gender?.name
    }

    @TypeConverter
    fun toGenderDb(name: String?): GenderDb? {
        return name?.let { GenderDb.valueOf(it) }
    }
}