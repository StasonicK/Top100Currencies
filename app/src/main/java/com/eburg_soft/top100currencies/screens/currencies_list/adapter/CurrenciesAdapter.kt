package com.eburg_soft.top100currencies.screens.currencies_list.adapter

import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.eburg_soft.top100currencies.R
import com.eburg_soft.top100currencies.screens.base.BaseAdapter
import com.eburg_soft.top100currencies.screens.chart.ChartActivity
import com.eburg_soft.top100currencies.screens.currencies_list.adapter.CurrenciesAdapter.CurrencyViewHolder
import kotlinx.android.synthetic.main.recycler_view_item.view.image_currency_icon
import kotlinx.android.synthetic.main.recycler_view_item.view.text_currency_market_cap
import kotlinx.android.synthetic.main.recycler_view_item.view.text_currency_name
import kotlinx.android.synthetic.main.recycler_view_item.view.text_currency_price
import kotlinx.android.synthetic.main.recycler_view_item.view.text_currency_sym

class CurrenciesAdapter : BaseAdapter<CurrencyViewHolder>() {

    private lateinit var currenciesList: ArrayList<CurrenciesAdapter.Currency>

    //create ViewHolder and initialise views for list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return CurrencyViewHolder(v)
    }

    //ViewHolder realisation
    class CurrencyViewHolder(view: View) : BaseAdapter.BaseViewHolder(view) {

        var id: String = ""
        var symbol: String = ""
        var name: String = ""
        var image: String = ""
        var marketCap: String = ""
        var marketCapRank: Int = 0
        var marketCapChangePercentage24h: Float = 0.0f
        var priceChangePercentage24h: Float = 0.0f
        var totalVolume: Float = 0.0f
        var ath: Float = 0.0f
        var athChangePercentage: Float = 0.0f
        var circulatingSupply: Double = 0.0
        var totalSupply: Long = 0

        init {
            //listener for clicking on list elements
            itemView.setOnClickListener {
                var intent = Intent(itemView.context, ChartActivity::class.java)
                intent.putExtra("id", id)
                    .putExtra("name", name)
                    .putExtra("symbol", symbol)
                    .putExtra("image", image)
                    .putExtra("marketCapRank", marketCapRank)
                    .putExtra("marketCap", marketCap)
                    .putExtra("marketCapChangePercentage24h", marketCapChangePercentage24h)
                    .putExtra("priceChangePercentage24h", priceChangePercentage24h)
                    .putExtra("totalVolume", totalVolume)
                    .putExtra("ath", ath)
                    .putExtra("athChangePercentage", athChangePercentage)
                    .putExtra("circulatingSupply", circulatingSupply)
                    .putExtra("totalSupply", totalSupply)
                itemView.context.startActivity(intent)
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
                id = item.id.toString()
                symbol = item.symbol.toString()
                name = item.name.toString()
                image = item.image.toString()
                marketCapRank = item.marketCapRank
                marketCapChangePercentage24h = item.marketCapChangePercentage24h
                priceChangePercentage24h = item.priceChangePercentage24h
                totalVolume = item.totalVolume
                ath = item.ath
                athChangePercentage = item.athChangePercentage
                circulatingSupply = item.circulatingSupply
                totalSupply = item.totalSupply
            }
        }
    }

    //data class for an element of the list
    data class Currency(
        val id: String?,
        val symbol: String?,
        val name: String?,
        val image: String?,
        val price: Float,
        val marketCap: String?,
        val marketCapRank: Int,
        val totalVolume: Float,
        val priceChangePercentage24h: Float,
        val marketCapChangePercentage24h: Float,
        val circulatingSupply: Double,
        val totalSupply: Long,
        val ath: Float,
        val athChangePercentage: Float
    ) : Parcelable {

        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readFloat(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readFloat(),
            parcel.readFloat(),
            parcel.readFloat(),
            parcel.readDouble(),
            parcel.readLong(),
            parcel.readFloat(),
            parcel.readFloat()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(id)
            parcel.writeString(symbol)
            parcel.writeString(name)
            parcel.writeString(image)
            parcel.writeFloat(price)
            parcel.writeString(marketCap)
            parcel.writeInt(marketCapRank)
            parcel.writeFloat(totalVolume)
            parcel.writeFloat(priceChangePercentage24h)
            parcel.writeFloat(marketCapChangePercentage24h)
            parcel.writeDouble(circulatingSupply)
            parcel.writeLong(totalSupply)
            parcel.writeFloat(ath)
            parcel.writeFloat(athChangePercentage)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Creator<Currency> {
            override fun createFromParcel(parcel: Parcel): Currency {
                return Currency(parcel)
            }

            override fun newArray(size: Int): Array<Currency?> {
                return arrayOfNulls(size)
            }
        }
    }
}














