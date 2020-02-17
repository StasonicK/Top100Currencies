package com.eburg_soft.top100currencies.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.eburg_soft.top100currencies.R
import kotlinx.android.synthetic.main.recycler_view_item.view.image_currency_icon
import kotlinx.android.synthetic.main.recycler_view_item.view.text_currency_market_cap
import kotlinx.android.synthetic.main.recycler_view_item.view.text_currency_name
import kotlinx.android.synthetic.main.recycler_view_item.view.text_currency_price
import kotlinx.android.synthetic.main.recycler_view_item.view.text_currency_sym

class CurrenciesAdapter : BaseAdapter<CurrenciesAdapter.CurrencyViewHolder>() {
    //create ViewHolder and initialize views from the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_view_item,
            parent, false
        )
        return CurrencyViewHolder(v)
    }

    //realization of the viewHolder
    class CurrencyViewHolder(view: View) : BaseAdapter.BaseViewHolder(view) {

        init {
            //click listener on the list elements
            itemView.setOnClickListener {
            }
        }

        //bind elements of the list to RecyclerView and fill by data
        override fun bind(item: Any) {
            let {
                item as Currency
                Glide.with(view.context).load(item.image).into(view.image_currency_icon)
                view.text_currency_sym.text = item.symbol
                view.text_currency_name.text = item.name
                view.text_currency_market_cap.text = item.marketCap
                view.text_currency_price.text = item.price.toString()
            }
        }
    }

    //data class for element of the list
    data class Currency(
        val id: String,
        val symbol: String,
        val name: String,
        val image: String,
        val price: Float,
        val marketCap: String,
        val marketCapRank: Int,
        val totalVolume: Float,
        val priceChangePercentage24h: Float,
        val marketCapChangePercentage24h: Float,
        val circulatingSupply: Double,
        val totalSupply: Long,
        val ath: Float,
        val athChangePercentage: Float
    )
}