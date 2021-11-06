package com.yago.coin.domain.interactor

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.yago.coin.data.db.entity.Data
import com.yago.coin.data.repository.MainRepository
import com.yago.coin.data.utils.Resource
import javax.inject.Inject

class MainInteractor @Inject constructor(private val app: Application, private val mainRepository: MainRepository) {

    private val dataFilter = MutableLiveData<String>()

    val dataLD: LiveData<Resource<Data>> = Transformations
        .switchMap(dataFilter) {
            mainRepository.getData()
        }

    fun getData() {
        dataFilter.value = "fire!"
    }


}