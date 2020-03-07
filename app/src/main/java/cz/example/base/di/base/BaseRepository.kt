package cz.example.base.di.base

import cz.example.base.data.rest.ws.DummyWebService
import cz.example.base.prefs.PrefManager
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseRepository : KoinComponent {

    protected val prefs by inject<PrefManager>()
    protected val webService by inject<DummyWebService>()


}