package com.tobuy.screen.main

import com.tobuy.base.FlowViewModel
import com.tobuy.persistence.database.activityrecords.ActivityRecordsFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val activityRecordsFlow: ActivityRecordsFlow
) : FlowViewModel<MainState, MainEvent>() {
    override val initialUi = MainState(
        product = ""
    )

    override val uiFlow: Flow<MainState> = combine(
        activityRecordsFlow(Unit)
    ) { activity ->
        MainState(
            product = activity.size.toString()
        )
    }

    override suspend fun handleEvent(event: MainEvent) {
        when (event) {
            is MainEvent.ProductPicked -> TODO()
        }
    }
}