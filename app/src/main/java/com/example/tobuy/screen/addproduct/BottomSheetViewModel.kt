package com.example.tobuy.screen.addproduct

import com.example.tobuy.base.FlowViewModel
import com.example.tobuy.sharedText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class BottomSheetViewModel @Inject constructor(
) : FlowViewModel<BottomSheetState, BottomSheetEvent>() {
    override val initialUi = BottomSheetState(
        sharedText = ""
    )

    override val uiFlow: Flow<BottomSheetState> = combine(
        sharedText,
        flowOf(Unit),
    ) { sharedText, _ ->
        BottomSheetState(
            sharedText = sharedText ?: ""
        )
    }

    override suspend fun handleEvent(event: BottomSheetEvent) {

    }
}