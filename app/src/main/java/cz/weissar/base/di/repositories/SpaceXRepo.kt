package cz.weissar.base.di.repositories

import cz.weissar.base.data.services.SpaceXAPI
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SpaceXRepo : KoinComponent {

    private val api by inject<SpaceXAPI>()

    suspend fun fetchLaunches() = api.getAllLaunches()
}