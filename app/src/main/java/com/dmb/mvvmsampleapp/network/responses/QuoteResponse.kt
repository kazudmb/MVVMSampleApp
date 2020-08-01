package com.dmb.mvvmsampleapp.network.responses

import com.dmb.mvvmsampleapp.data.db.entities.Quote

data class QuotesResponse(
    val isSuccessful: Boolean,
    val quotes: List<Quote>
)