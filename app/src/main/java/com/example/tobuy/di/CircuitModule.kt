package com.example.tobuy.di

import com.slack.circuit.foundation.CircuitConfig
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import dagger.multibindings.Multibinds

@ContributesTo(AppScope::class)
@Module
interface CircuitModule {
    @Multibinds
    fun presenterFactories(): Set<Presenter.Factory>

    @Multibinds
    fun viewFactories(): Set<Ui.Factory>

    companion object {
        @Provides
        fun provideCircuit(
            presenterFactories: @JvmSuppressWildcards Set<Presenter.Factory>,
            uiFactories: @JvmSuppressWildcards Set<Ui.Factory>
        ): CircuitConfig {
            return CircuitConfig.Builder()
                .addPresenterFactories(presenterFactories)
                .addUiFactories(uiFactories)
                .build()
        }
    }
}