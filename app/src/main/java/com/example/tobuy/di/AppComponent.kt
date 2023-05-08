package com.example.tobuy.di

import com.squareup.anvil.annotations.MergeComponent
import dagger.Component

@MergeComponent(
    scope = AppScope::class,
    modules = [
        CircuitModule::class
    ]
)
@SingleIn(AppScope::class)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }
}