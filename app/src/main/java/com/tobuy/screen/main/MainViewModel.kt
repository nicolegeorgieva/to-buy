package com.tobuy.screen.main

import com.tobuy.base.FlowViewModel
import com.tobuy.persistence.database.productrecords.ProductRecordsFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val productRecordsFlow: ProductRecordsFlow
) : FlowViewModel<MainState, MainEvent>() {
    override val initialUi = MainState(
        product = ""
    )

    override val uiFlow: Flow<MainState> = combine(
        productRecordsFlow(Unit)
    ) { products ->
        MainState(
            product = products.size.toString()
        )
    }

    override suspend fun handleEvent(event: MainEvent) {
        when (event) {
            is MainEvent.ProductPicked -> TODO()
        }
    }
}