package com.example.purchaselist.ui.screens

import android.content.Context
import android.graphics.Color
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.purchaselist.R
import com.example.purchaselist.data.ColorResources
import com.example.purchaselist.data.StringResources

class MainFragmentScreen(context: Context) {

    val containerView = LinearLayout(context).apply {
        layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT,
        )
        orientation = LinearLayout.VERTICAL
    }

    val toolbar = Toolbar(context).apply {
        layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )
        title = StringResources.purchases
        setTitleTextColor(Color.WHITE)
        setBackgroundColor(ColorResources.blue)
        menu.apply {
            add(StringResources.addItem).setIcon(R.drawable.ic_add)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        }
    }


    val recyclerView = RecyclerView(context).apply {
        layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            0,
        ).apply {
            weight = 3f
        }
        layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    init {
        containerView.apply {
            addView(toolbar)
            addView(recyclerView)
        }
    }
}