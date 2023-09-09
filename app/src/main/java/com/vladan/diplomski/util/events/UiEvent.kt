package com.vladan.diplomski.util.events

sealed interface UiEvent {
    data class ToastEvent(val value: String) : UiEvent
}