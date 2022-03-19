package cz.weissar.base.ui.dummy

import androidx.lifecycle.MutableLiveData
import cz.weissar.base.data.model.response.DummyResponse
import cz.weissar.base.di.base.BaseViewModel
import cz.weissar.base.di.repositories.DummyRepository

class DummyViewModel(private val dummyRepo: DummyRepository) : BaseViewModel() {

    val dummyData = MutableLiveData<List<DummyResponse>>()

    fun getOrLoadDummy() {
        launch {
            dummyData.postValue(dummyRepo.getDummy())
        }
    }
}