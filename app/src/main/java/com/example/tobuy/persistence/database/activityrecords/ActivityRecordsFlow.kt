package com.example.tobuy.persistence.database.activityrecords

import com.employees.base.FlowAction
import com.example.tobuy.persistence.database.AppDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ActivityRecordsFlow @Inject constructor(
    private val appDatabase: AppDatabase
) : FlowAction<Unit, List<ActivityRecordEntity>>() {
    override fun createFlow(input: Unit): Flow<List<ActivityRecordEntity>> {
        return appDatabase.activityRecordDao().findAll()
    }
}
