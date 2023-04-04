package com.tobuy.persistence.database.activityrecords

import com.employees.base.Action
import com.tobuy.persistence.database.AppDatabase
import java.util.*
import javax.inject.Inject

class DeleteActivityRecordAct @Inject constructor(
    private val appDatabase: AppDatabase
) : Action<UUID, Unit>() {
    override suspend fun action(input: UUID) {
        appDatabase.activityRecordDao().deleteById(input)
    }
}