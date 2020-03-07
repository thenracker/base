package cz.example.base.di.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cz.example.base.common.enums.State
import kotlinx.coroutines.*

abstract class BaseViewModel : ViewModel() {

    private val job = SupervisorJob() // vlákno pro zpracování
    private val handler = CoroutineExceptionHandler { _, ex -> ex.printStackTrace() }
    private val scope = CoroutineScope(Dispatchers.Default + job + handler)

    protected val state = MutableLiveData<State>()

    protected fun launch(failure: ((Exception) -> Unit)? = null,
                         defaultState: MutableLiveData<State>? = state,
                         operation: (suspend () -> Unit) ) {
        scope.launch {
            defaultState?.postValue(State.Loading)
            try {
                operation()
                defaultState?.postValue(State.Success)
            } catch (e: Exception) {
                failure?.invoke(e)
                defaultState?.postValue(State.Failure(e))
            }
        }
    }

    /**
     * How to call launch from children
     */
    private fun test() {
        launch {

        }

        launch (
            operation = {

            },
            failure = {

            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel() // optional
    }
}