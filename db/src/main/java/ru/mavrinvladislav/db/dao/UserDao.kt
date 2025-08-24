package ru.mavrinvladislav.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.mavrinvladislav.db.datasource.UserLocalDataSource
import ru.mavrinvladislav.db.entity.UserDb

@Dao
internal interface UserDao : UserLocalDataSource {

    @Query("SELECT * FROM users")
    override fun getUsers(): Flow<List<UserDb>>

    @Query("SELECT * FROM users WHERE id = :id")
    override suspend fun getUser(id: Long): UserDb

    @Query("DELETE FROM users")
    override suspend fun clearUsers()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun addUsers(users: List<UserDb>)

}