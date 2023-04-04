package com.tobuy.persistence.database.productrecords

import com.employees.base.FlowAction
import com.tobuy.persistence.database.AppDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRecordsFlow @Inject constructor(
    private val appDatabase: AppDatabase
) : FlowAction<Unit, List<ProductRecordEntity>>() {
    override fun createFlow(input: Unit): Flow<List<ProductRecordEntity>> =
        appDatabase.productRecordDao().findAll()
}
