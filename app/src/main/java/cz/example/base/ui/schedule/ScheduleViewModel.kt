package cz.example.base.ui.schedule

import androidx.lifecycle.MutableLiveData
import cz.example.base.di.base.BaseViewModel

class ScheduleViewModel : BaseViewModel() {

    val schedule = MutableLiveData<List<String>>()

    fun loadSchedule() {
        launch {
            // provolat rest todo
            // schedule.postValue(result)
        }
    }
}