package com.eburg_soft.top100currencies.ui.adapter

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import android.view.View
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CurrenciesAdapter1
//    : ListAdapter<CurrenciesAdapter1.Currency, CurrenciesAdapter1.CurrenciesListViewHolder>
{



    class CurrenciesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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
    ): Parcelable {

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
        ) {
        }

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