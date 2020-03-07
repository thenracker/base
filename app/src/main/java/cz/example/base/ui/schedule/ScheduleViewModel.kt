package cz.example.base.ui.schedule

import androidx.lifecycle.MutableLiveData
import cz.example.base.data.rest.dto.CalendarScheduleItemResponse
import cz.example.base.di.base.BaseViewModel

class ScheduleViewModel : BaseViewModel() {

    val schedule = MutableLiveData<List<CalendarScheduleItemResponse>>()

    fun loadSchedule() {
        launch {
            // provolat rest todo
            // schedule.postValue(result)
        }
    }
}