package cz.weissar.base.di.repositories

import cz.weissar.base.data.db.dao.DummyDao
import cz.weissar.base.di.base.BaseRepository
import org.koin.core.inject

class DummyRepository : BaseRepository() {

    private val dummyDao by inject<DummyDao>()

    /**
     * @sample get for REST calls
     */
    suspend fun getDummy() = dummyWebService.getDummyResponseList()

    /**
     * @sample load for DB calls
     */
    suspend fun loadDummy() = dummyDao.getAll()
}