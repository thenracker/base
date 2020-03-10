package cz.weissar.base.di.base

import cz.weissar.base.data.rest.ws.DummyWebService
import cz.weissar.base.common.prefs.PrefManager
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseRepository : KoinComponent {

    protected val prefs by inject<PrefManager>()

    protected val dummyWebService by inject<DummyWebService>()

}