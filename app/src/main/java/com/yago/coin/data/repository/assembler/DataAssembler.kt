package com.yago.coin.data.repository.assembler

import android.app.Application
import com.yago.coin.data.api.response.DataResponse
import com.yago.coin.data.db.CoinDb
import com.yago.coin.data.db.entity.Data

object DataAssembler {

    fun assembleData(coinDb: CoinDb, dataResponse: DataResponse, app: Application) {
        coinDb.runInTransaction {
            coinDb.dataDao().insert(Data("id5", "Data 1"))
        }
    }
}