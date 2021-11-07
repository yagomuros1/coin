package com.yago.coin.data.repository.assembler

import android.app.Application
import com.yago.coin.data.api.response.RateResponse
import com.yago.coin.data.api.response.TransactionResponse
import com.yago.coin.data.db.CoinDb
import com.yago.coin.data.db.entity.Rate
import com.yago.coin.data.db.entity.Trade

object DataAssembler {

    fun assembleRates(coinDb: CoinDb, rates: List<RateResponse>, app: Application) {
        coinDb.runInTransaction {
            coinDb.rateDao().removeAllData()
            rates.forEach { rate ->
                coinDb.rateDao().save(Rate(from = rate.from, to = rate.to, rate = rate.rate))
            }
        }
    }

    fun assembleTrades(coinDb: CoinDb, trades: List<TransactionResponse>, app: Application) {
        coinDb.runInTransaction {

            trades.forEach { trade ->
                coinDb.tradeDao().save(Trade(id = null, sku = trade.sku, amount = trade.amount, currency = trade.currency))
            }
        }
    }
}