package com.eburg_soft.top100currencies.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.eburg_soft.top100currencies.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.eburg_soft.top100currencies.ui.fragment.CurrenciesListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, CurrenciesListFragment(), null)
                .commit()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item!!.itemId) {
            R.id.action_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        showAd()
    }


    private fun showAd() {
        if (mInterstitialAd.isLoaded) {
            mInterstitialAd.show()
        }
    }
}
