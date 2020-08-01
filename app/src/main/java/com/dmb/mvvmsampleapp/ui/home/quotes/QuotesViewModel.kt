package com.dmb.mvvmsampleapp.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.dmb.mvvmsampleapp.repository.QuotesRepository
import com.dmb.mvvmsampleapp.util.lazyDeferred

class QuotesViewModel(
    private val repository: QuotesRepository
) : ViewModel() {

    val quotes by lazyDeferred {
        repository.getQuotes()
    }
}