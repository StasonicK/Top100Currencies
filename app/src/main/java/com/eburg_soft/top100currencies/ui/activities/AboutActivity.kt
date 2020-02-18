package com.eburg_soft.top100currencies.ui.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eburg_soft.top100currencies.R
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.activity_about.adView
import kotlinx.android.synthetic.main.activity_about.buttonRateApp

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        buttonRateApp.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=${applicationContext.packageName}")))
        }

    }

}
