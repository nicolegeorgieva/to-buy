package com.tobuy.persistence.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tobuy.persistence.database.activityrecords.ActivityRecordDao
import com.tobuy.persistence.database.activityrecords.ActivityRecordEntity

@Database(
    entities = [
        ActivityRecordEntity::class
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(
    GeneralTypeConverters::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun activityRecordDao(): ActivityRecordDao

    companion object {
        private const val DB_NAME = "to-buy.db"

        fun create(applicationContext: Context): AppDatabase {
            return Room.databaseBuilder(
                applicationContext, AppDatabase::class.java, DB_NAME
            ).build()
        }
    }
}