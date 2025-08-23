package ru.mavrinvladislav.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.mavrinvladislav.db.datasource.UserLocalDataSource
import ru.mavrinvladislav.db.entity.UserDb

@Dao
internal interface UserDao : UserLocalDataSource {

    @Query("SELECT * FROM users LIMIT :limit OFFSET :offset")
    override fun getUsersPage(limit: Int, offset: Int): Flow<List<UserDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun addUsers(users: List<UserDb>)

}