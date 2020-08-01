package com.dmb.mvvmsampleapp.ui.home.quotes

import com.dmb.mvvmsampleapp.R
import com.dmb.mvvmsampleapp.data.db.entities.Quote
import com.dmb.mvvmsampleapp.databinding.ItemQuoteBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(
    private val quote: Quote
) : BindableItem<ItemQuoteBinding>() {
    override fun getLayout(): Int = R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.setQuote(quote)
    }

}