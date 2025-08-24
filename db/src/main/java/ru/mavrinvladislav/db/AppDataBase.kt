package ru.mavrinvladislav.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.mavrinvladislav.db.dao.UserDao
import ru.mavrinvladislav.db.entity.UserDb

@Database(
    entities = [UserDb::class],
    version = 1,
    exportSchema = false,
)
internal abstract class AppDatabase : RoomDatabase() {
    companion object {

        private const val DB_NAME = "main.db"
        private var instance: AppDatabase? = null
        private val MONITOR = Any()

        fun getInstance(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            synchronized(MONITOR) {
                val db = Room.databaseBuilder(
                    context = context,
                    klass = AppDatabase::class.java,
                    name = DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                instance = db
                return db
            }
        }
    }

    abstract fun userDao(): UserDao
}