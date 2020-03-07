package cz.example.base.ui.schedule

import androidx.lifecycle.MutableLiveData
import cz.example.base.data.rest.dto.CalendarScheduleItemResponse
import cz.example.base.data.rest.dto.DummyResponse
import cz.example.base.di.base.BaseViewModel
import cz.example.base.di.repositories.ScheduleRepository

class ScheduleViewModel(private val scheduleRepo: ScheduleRepository) : BaseViewModel() {

    val schedule = MutableLiveData<List<DummyResponse>>()

    fun loadSchedule() { // z rest
        launch {
            schedule.postValue(scheduleRepo.loadSchedule())
        }
    }

    fun loadDummy() { // z db
        launch {
            val dummyList = scheduleRepo.loadDbDummy()
            // todo - volat cokoliv co potřebuji
        }
    }
}