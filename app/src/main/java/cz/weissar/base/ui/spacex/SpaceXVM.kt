package cz.weissar.base.ui.spacex

import cz.weissar.base.common.enums.None
import cz.weissar.base.common.enums.State
import cz.weissar.base.data.model.response.RocketLaunch
import cz.weissar.base.di.base.BaseVM
import cz.weissar.base.di.repositories.SpaceXRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SpaceXVM(
    private val repo: SpaceXRepo,
) : BaseVM() {

    private val _viewState = MutableStateFlow(LaunchesScreenState())
    val viewState: StateFlow<LaunchesScreenState> = _viewState

    fun fetchItems() = launch {
        repo.fetchLaunches()
            .let { list ->
                setState { copy(list = list) }
            }
    }

    private suspend fun setState(reducer: LaunchesScreenState.() -> LaunchesScreenState) {
        _viewState.emit(viewState.value.reducer())
    }
}

data class LaunchesScreenState(
    val list: List<RocketLaunch> = emptyList(),
    val status: State = None,
)