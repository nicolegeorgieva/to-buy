package com.tobuy.persistence.database.productrecords

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tobuy.domain.data.ProductInfo
import java.util.*

@Entity(tableName = "product_records")
data class ProductRecordEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: UUID,
    @ColumnInfo(name = "productInfo")
    val productInfo: ProductInfo
)
