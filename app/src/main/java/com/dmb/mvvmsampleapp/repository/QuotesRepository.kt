package com.dmb.mvvmsampleapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dmb.mvvmsampleapp.data.db.AppDatabase
import com.dmb.mvvmsampleapp.data.db.entities.Quote
import com.dmb.mvvmsampleapp.network.MyApi
import com.dmb.mvvmsampleapp.network.responses.SafeApiRequest
import com.dmb.mvvmsampleapp.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuotesRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever {
            saveQuotes(it)
        }
    }

    suspend fun getQuotes(): LiveData<List<Quote>>{
        return withContext(Dispatchers.IO){
            fetchQuotes()
            db.getQuoteDao().getQuotes()
        }
    }

    private fun saveQuotes(quotes: List<Quote>) {
        Coroutines.io {
            db.getQuoteDao().saveAllQuotes(quotes)
        }
    }

    private suspend fun fetchQuotes() {
        if (isFetchNeeded()) {
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
        }
    }

    private fun isFetchNeeded(): Boolean {
        return true
    }

}