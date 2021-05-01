package com.example.purchaselist.ui.screens

import android.content.Context
import android.graphics.Color
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.FragmentContainerView

class MainActivityScreen(context: Context) {

    companion object{
        const val ID_FRAGMENT_VIEW = 1
    }

    val fragmentContainerView = FragmentContainerView(context).apply {
        layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT,
        )
        id = ID_FRAGMENT_VIEW
        setBackgroundColor(Color.WHITE)
    }
}