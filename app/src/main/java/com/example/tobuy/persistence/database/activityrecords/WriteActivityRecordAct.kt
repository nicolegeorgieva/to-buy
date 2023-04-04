package com.example.tobuy.persistence.database.activityrecords

import com.employees.base.Action
import com.example.tobuy.persistence.database.AppDatabase
import javax.inject.Inject

class WriteActivityRecordAct @Inject constructor(
    private val appDatabase: AppDatabase
) : Action<ActivityRecordEntity, Unit>() {
    override suspend fun action(input: ActivityRecordEntity) {
        appDatabase.activityRecordDao().save(input)
    }
}