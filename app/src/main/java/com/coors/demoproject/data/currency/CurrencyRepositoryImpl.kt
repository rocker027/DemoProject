package com.coors.demoproject.data.currency

//import com.squareup.moshi.Moshi
//import com.squareup.moshi.addAdapter
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor() : CurrencyRepository {
    override fun fetchCurrencyList(): List<CurrencyInfo> {
//        private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
//
        return emptyList()
    }

    fun mockJsonString() = """
             [
                  {
                    "id": "BTC",
                    "name": "Bitcoin",
                    "symbol": "BTC"
                  },
                  {
                    "id": "ETH",
                    "name": "Ethereum",
                    "symbol": "ETH"
                  },
                  {
                    "id": "XRP",
                    "name": "XRP",
                    "symbol": "XRP"
                  },
                  {
                    "id": "BCH",
                    "name": "Bitcoin Cash",
                    "symbol": "BCH"
                  },
                  {
                    "id": "LTC",
                    "name": "Litecoin",
                    "symbol": "LTC"
                  },
                  {
                    "id": "EOS",
                    "name": "EOS",
                    "symbol": "EOS"
                  },
                  {
                    "id": "BNB",
                    "name": "Binance Coin",
                    "symbol": "BNB"
                  },
                  {
                    "id": "LINK",
                    "name": "Chainlink",
                    "symbol": "LINK"
                  },
                  {
                    "id": "NEO",
                    "name": "NEO",
                    "symbol": "NEO"
                  },
                  {
                    "id": "ETC",
                    "name": "Ethereum Classic",
                    "symbol": "ETC"
                  },
                  {
                    "id": "ONT",
                    "name": "Ontology",
                    "symbol": "ONT"
                  },
                  {
                    "id": "CRO",
                    "name": "Crypto.com Chain",
                    "symbol": "CRO"
                  },
                  {
                    "id": "CUC",
                    "name": "Cucumber",
                    "symbol": "CUC"
                  },
                  {
                    "id": "USDC",
                    "name": "USD Coin",
                    "symbol": "USDC"
                  }
            ]   
    """.trimIndent()
}