package com.eburg_soft.top100currencies

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.eburg_soft.top100currencies.ui.activities.AboutActivity
import com.eburg_soft.top100currencies.ui.fragments.CurrenciesListFragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd

class MainActivity : AppCompatActivity() {
    private lateinit var mInterstitialAd: InterstitialAd

    private val pageBannerAds = "ca-app-pub-8721640890137447/7185287810"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = pageBannerAds
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, CurrenciesListFragment(), null)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item!!.itemId) {
            R.id.action_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showAd() {
        if (mInterstitialAd.isLoaded) {
            mInterstitialAd.show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        showAd()
    }
}
