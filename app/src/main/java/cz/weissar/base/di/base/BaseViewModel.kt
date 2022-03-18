package cz.weissar.base.di.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cz.weissar.base.common.enums.Failure
import cz.weissar.base.common.enums.Loading
import cz.weissar.base.common.enums.State
import cz.weissar.base.common.enums.Success
import kotlinx.coroutines.*

abstract class BaseViewModel : ViewModel() {

    private val job = Job() // vlákno pro zpracování
    private val scope = CoroutineScope(Dispatchers.Default + job)

    val state = MutableLiveData<State>()

    protected fun <Result> launch(
        onError: ((Exception) -> Unit)? = null,
        defaultState: MutableLiveData<State>? = state,
        block: (suspend () -> Result)
    ) {
        scope.launch(
            CoroutineExceptionHandler { _, error ->
                when (error) {
                    is Exception -> {
                        onError?.invoke(error)
                        defaultState?.postValue(Failure(error))
                    }
                    else -> {
                        // Optional
                    }
                }
            }) {
            defaultState?.postValue(Loading)
            block().let(::Success).also { defaultState?.postValue(it) }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel() // optional
    }
}