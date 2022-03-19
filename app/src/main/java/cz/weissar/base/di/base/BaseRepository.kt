package cz.weissar.base.di.base

import cz.weissar.base.data.services.DummyWebService
import cz.weissar.base.common.prefs.PrefManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseRepository : KoinComponent {

    protected val prefs by inject<PrefManager>()

    protected val dummyWebService by inject<DummyWebService>()

}