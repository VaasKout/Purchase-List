package com.example.purchaselist.ui.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.commit
import com.example.purchaselist.R
import com.example.purchaselist.data.ColorResources
import com.example.purchaselist.ui.fragments.MainFragment
import com.example.purchaselist.ui.screens.MainActivityScreen
import com.example.purchaselist.ui.screens.MainActivityScreen.Companion.ID_FRAGMENT_VIEW

class MainActivity : AppCompatActivity() {
    private val layout by lazy { MainActivityScreen(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ColorResources.blueDark
        setContentView(layout.fragmentContainerView)
        setUpMainFragment()
    }

    private fun setUpMainFragment() {
        supportFragmentManager.commit {
            replace(ID_FRAGMENT_VIEW, MainFragment())
        }
    }
}