package cz.example.base.common.enums

sealed class State {

    object Loading : State()
    object Success : State()
    class Failure(e: Exception) : State()
}