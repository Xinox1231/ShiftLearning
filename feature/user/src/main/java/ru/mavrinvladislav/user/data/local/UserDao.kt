package ru.mavrinvladislav.user.data.local

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.mavrinvladislav.user.data.local.entity.UserDb

@Dao
interface UserDao {

    @Query("SELECT * FROM users LIMIT :limit OFFSET :offset")
    fun getUsersPage(limit: Int, offset: Int): Flow<List<UserDb>>

}