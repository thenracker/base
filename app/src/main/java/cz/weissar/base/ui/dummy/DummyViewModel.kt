package cz.weissar.base.ui.dummy

import androidx.lifecycle.MutableLiveData
import cz.weissar.base.data.rest.dto.DummyResponse
import cz.weissar.base.di.base.BaseViewModel
import cz.weissar.base.di.repositories.DummyRepository

class DummyViewModel(private val dummyRepo: DummyRepository) : BaseViewModel() {

    val schedule = MutableLiveData<List<DummyResponse>>()

    fun getOrLoadDummy() {
        launch {
            // if (alreadyDownloaded) dummyRepo.loadDummy()
            // else
            schedule.postValue(dummyRepo.getDummy())
        }
    }
}