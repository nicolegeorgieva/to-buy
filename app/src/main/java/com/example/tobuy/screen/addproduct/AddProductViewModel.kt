package com.example.tobuy.screen.addproduct

import com.example.tobuy.base.FlowViewModel
import com.example.tobuy.sharedText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class AddProductViewModel @Inject constructor(
) : FlowViewModel<AddProductState, AddProductEvent>() {
    override val initialUi = AddProductState(
        sharedText = ""
    )

    override val uiFlow: Flow<AddProductState> = combine(
        sharedText,
        flowOf(Unit),
    ) { sharedText, _ ->
        AddProductState(
            sharedText = sharedText ?: ""
        )
    }

    override suspend fun handleEvent(event: AddProductEvent) {

    }
}