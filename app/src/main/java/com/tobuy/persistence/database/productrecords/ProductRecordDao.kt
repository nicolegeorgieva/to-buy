package com.tobuy.persistence.database.productrecords

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface ProductRecordDao {
    // Create or Update
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(record: ProductRecordEntity)

    // Delete
    @Query("DELETE FROM product_records WHERE id = :id")
    suspend fun deleteById(id: UUID)

    // Reads all weight records
    @Query("SELECT * FROM weight_records")
    fun findAll(): Flow<List<ProductRecordEntity>>
}