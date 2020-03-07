package cz.example.base.di.repositories

import cz.example.base.di.base.BaseRepository

class ScheduleRepository : BaseRepository() {

    suspend fun loadSchedule() = webService.getDummyResponseList()
}