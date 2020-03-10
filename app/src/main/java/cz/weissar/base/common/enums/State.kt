package cz.weissar.base.common.enums

sealed class State {

    object Loading : State()
    object Success : State()
    class Failure(val e: Exception) : State()
}