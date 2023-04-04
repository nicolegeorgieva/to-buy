package com.example.tobuy.screen.main

sealed interface MainEvent {
    data class ProductPicked(val product: String) : MainEvent
}