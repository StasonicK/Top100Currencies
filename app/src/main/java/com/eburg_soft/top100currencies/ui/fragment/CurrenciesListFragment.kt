package com.eburg_soft.top100currencies.ui.fragment

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.eburg_soft.top100currencies.R
import com.eburg_soft.top100currencies.common.App
import com.eburg_soft.top100currencies.mvp.contract.CurrenciesContract
import com.eburg_soft.top100currencies.mvp.presenter.CurrenciesPresenter
import com.eburg_soft.top100currencies.ui.adapter.BaseAdapter
import com.eburg_soft.top100currencies.ui.adapter.CurrenciesAdapter
import kotlinx.android.synthetic.main.activity_main.progress_bar
import java.util.ArrayList
import javax.inject.Inject

class CurrenciesListFragment : BaseListFragment(), CurrenciesContract.View {
    private val BUNDLE_ALBUM_LIST = "currencyList"
    private val BUNDLE_RECYCLER_VIEW_POSITION = "recyclerPosition"

    @Inject
    lateinit var presenter: CurrenciesPresenter

    private var currencyList: ArrayList<CurrenciesAdapter.Currency>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_currencies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.appComponent.inject(this)
        presenter.attach(this)
        presenter.makeList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        if (savedInstanceState != null) {
            currencyList = savedInstanceState.getParcelableArrayList(BUNDLE_ALBUM_LIST)
            //todo pull "currencyList" into listAdapter after shifting from RecyclerView.Adapter to ListAdapter
            val listState: Parcelable = savedInstanceState.getParcelable(BUNDLE_RECYCLER_VIEW_POSITION)!!
            recyclerView.layoutManager?.onRestoreInstanceState(listState)
        } else if (arguments != null) {
            currencyList = arguments!!.getParcelableArrayList(BUNDLE_ALBUM_LIST)
            //todo pull "currencyList" into listAdapter after shifting from RecyclerView.Adapter to ListAdapter
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(BUNDLE_ALBUM_LIST, currencyList)
    }

    override fun createAdapterInstance(): BaseAdapter<*> {
        return CurrenciesAdapter()
    }

    override fun addCurrency(currency: CurrenciesAdapter.Currency) {
        viewAdapter.add(currency)
    }

    override fun notifyAdapter() {
        viewAdapter.notifyDataSetChanged()
    }

    override fun showProgress() {
        requireActivity().progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        requireActivity().progress_bar.visibility = View.INVISIBLE
    }

    override fun showErrorMessage(error: String?) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun refresh() {
        viewAdapter.items.clear()
        viewAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }
}
