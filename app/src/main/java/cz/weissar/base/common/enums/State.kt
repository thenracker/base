package cz.weissar.base.common.enums

sealed class State
object None: State()
object Loading : State()
class Success(val any: Any?) : State()
class Failure(val exception: Exception) : State()