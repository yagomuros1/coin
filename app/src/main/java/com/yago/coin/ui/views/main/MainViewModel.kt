package com.yago.coin.ui.views.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.yago.coin.data.db.entity.Data
import com.yago.coin.data.utils.Resource
import com.yago.coin.domain.interactor.MainInteractor
import javax.inject.Inject

class MainViewModel @Inject constructor(private val mainInteractor: MainInteractor) : ViewModel() {

    val data: LiveData<Resource<Data>> = mainInteractor.dataLD

    fun onResumeMainScreen() {
        mainInteractor.getData()
    }

}