package cz.weissar.base.di.base

import androidx.lifecycle.ViewModel
import cz.weissar.base.common.enums.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseVM : ViewModel() {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Default + job)

    val state = MutableStateFlow<State>(None)

    protected fun <Result> launch(
        onError: ((Exception) -> Unit)? = null,
        defaultState: MutableStateFlow<State>? = state,
        block: (suspend () -> Result),
    ) = scope.launch(
        // Handle possible exception
        CoroutineExceptionHandler { _, error ->
            when (error) {
                is Exception -> {
                    onError?.invoke(error)
                    defaultState?.tryEmit(Failure(error))
                }
                else -> {
                    // Optional
                }
            }
        }) {

        // Show loading
        defaultState?.emit(Loading)

        // Process operation and emit success
        block()
            .let(::Success)
            .also { defaultState?.emit(it) }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel() // optional
    }
}