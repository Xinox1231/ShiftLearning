package ru.mavrinvladislav.user.data.local

import androidx.room.Dao
import androidx.room.Query
import ru.mavrinvladislav.user.data.local.entity.UserDb

@Dao
interface UserDao {

    @Query("SELECT * FROM users LIMIT :limit OFFSET :offset")
    suspend fun getUsersPage(limit: Int, offset: Int): List<UserDb>

}