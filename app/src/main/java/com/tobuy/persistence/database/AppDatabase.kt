package com.tobuy.persistence.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tobuy.persistence.database.productrecords.ProductRecordDao
import com.tobuy.persistence.database.productrecords.ProductRecordEntity

@Database(
    entities = [
        ProductRecordEntity::class
    ],
    version = 1,
    exportSchema = true,
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun productRecordDao(): ProductRecordDao

    companion object {
        private const val DB_NAME = "to-buy.db"

        fun create(applicationContext: Context): AppDatabase {
            return Room.databaseBuilder(
                applicationContext, AppDatabase::class.java, DB_NAME
            ).build()
        }
    }
}