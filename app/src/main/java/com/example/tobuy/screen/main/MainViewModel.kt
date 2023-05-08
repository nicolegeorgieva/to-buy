package com.example.tobuy.screen.main

import com.example.tobuy.base.FlowViewModel
import com.example.tobuy.persistence.database.activityrecords.ActivityRecordsFlow
import com.example.tobuy.sharedText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val activityRecordsFlow: ActivityRecordsFlow
) : FlowViewModel<MainState, MainEvent>() {
    override val initialUi = MainState(
        product = "",
        sharedLink = null
    )

    override val uiFlow: Flow<MainState> = combine(
        activityRecordsFlow(Unit),
        sharedText
    ) { activity ->
        MainState(
            product = activity.size.toString(),
            sharedLink = sharedText.value
        )
    }

    override suspend fun handleEvent(event: MainEvent) {
        when (event) {
            is MainEvent.ProductPicked -> TODO()
        }
    }
}