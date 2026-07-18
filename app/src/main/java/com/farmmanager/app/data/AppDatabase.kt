package com.farmmanager.app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.farmmanager.app.data.dao.*
import com.farmmanager.app.data.entity.*

@Database(
    entities = [
        Flock::class,
        EggRecord::class,
        FeedRecord::class,
        HealthRecord::class,
        TransactionEntity::class,
        Reminder::class,
        Note::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun flockDao(): FlockDao
    abstract fun eggDao(): EggDao
    abstract fun feedDao(): FeedDao
    abstract fun healthDao(): HealthDao
    abstract fun transactionDao(): TransactionDao
    abstract fun reminderDao(): ReminderDao
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "farm_manager.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
