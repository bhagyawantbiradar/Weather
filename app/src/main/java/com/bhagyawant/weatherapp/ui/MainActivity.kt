package com.bhagyawant.weatherapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bhagyawant.weatherapp.R
import com.bhagyawant.weatherapp.ui.bookmars.BookmarksFragment
import com.bhagyawant.weatherapp.ui.map.MapsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) { // initial transaction should be wrapped like this
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MapsFragment.getInstance())
                .commitAllowingStateLoss()
        }
    }

}