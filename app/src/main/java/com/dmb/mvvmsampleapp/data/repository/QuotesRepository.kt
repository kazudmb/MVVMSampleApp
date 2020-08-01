package com.dmb.mvvmsampleapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dmb.mvvmsampleapp.data.db.AppDatabase
import com.dmb.mvvmsampleapp.data.db.entities.Quote
import com.dmb.mvvmsampleapp.data.network.MyApi
import com.dmb.mvvmsampleapp.data.network.responses.SafeApiRequest
import com.dmb.mvvmsampleapp.data.preference.PreferenceProvider
import com.dmb.mvvmsampleapp.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private const val MINIMUM_INTERVAL = 6

class QuotesRepository(
    private val api: MyApi,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
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

    private suspend fun fetchQuotes() {
        val lastSavedAt = prefs.getlastSaveAt()

        if (lastSavedAt == null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))) {
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
        }
    }

    private fun isFetchNeeded(savedAt: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(savedAt, LocalDateTime.now()) > MINIMUM_INTERVAL
    }

    private fun saveQuotes(quotes: List<Quote>) {
        Coroutines.io {
            prefs.savelastSaveAt(LocalDateTime.now().toString())
            db.getQuoteDao().saveAllQuotes(quotes)
        }
    }
}