package com.tobuy.persistence.database.productrecords

import com.employees.base.Action
import com.tobuy.persistence.database.AppDatabase
import javax.inject.Inject

class WriteProductRecordAct @Inject constructor(
    private val appDatabase: AppDatabase
) : Action<ProductRecordEntity, Unit>() {
    override suspend fun action(input: ProductRecordEntity) {
        appDatabase.productRecordDao().save(input)
    }
}
