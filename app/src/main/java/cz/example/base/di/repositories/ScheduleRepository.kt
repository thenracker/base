package cz.example.base.di.repositories

import cz.example.base.data.db.dao.DummyDao
import cz.example.base.di.base.BaseRepository
import org.koin.core.inject

class ScheduleRepository : BaseRepository() {

    private val dummyDao by inject<DummyDao>()

    suspend fun loadSchedule() = webService.getDummyResponseList()

    suspend fun loadDbDummy() = dummyDao.getAll()
}