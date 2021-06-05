package com.example.purchaselist.ui.screens

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.purchaselist.R
import com.example.purchaselist.data.ColorResources
import com.example.purchaselist.data.States
import com.example.purchaselist.data.StringResources
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.delay

class MainActivityScreen(context: Context) {


    val mainContainerView = FrameLayout(context).apply {
        layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT,
        )
        setBackgroundColor(Color.WHITE)
    }
    val frameContainerView = LinearLayout(context).apply {
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
            add(StringResources.deleteAll).setIcon(R.drawable.ic_delete_menu)
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

    val tipText = TextView(context).apply {
        layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT,
        ).apply {
            gravity = Gravity.CENTER
        }
        gravity = Gravity.CENTER
        text = StringResources.press
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            setTextAppearance(R.style.TextAppearance_MaterialComponents_Headline6)
        } else {
            setTextAppearance(context, R.style.TextAppearance_MaterialComponents_Headline6)
        }
        setTextColor(Color.GRAY)
        visibility = View.GONE
    }

    val floatingActionButton = FloatingActionButton(context).apply {
        layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            Gravity.BOTTOM or Gravity.END
        ).apply {
            setMargins(0,0, 64,64)
        }
        setImageResource(R.drawable.ic_add)
        backgroundTintList = States.blueLightState

    }

    init {
        mainContainerView.apply {
            addView(frameContainerView.apply {
                addView(toolbar)
                addView(recyclerView)
                addView(tipText)
            })
            addView(floatingActionButton)
        }
    }

    fun setFullState() {
        if (recyclerView.visibility == View.INVISIBLE) {
            recyclerView.visibility = View.VISIBLE
            tipText.visibility = View.GONE
        }

    }

    fun setEmptyState() {
        if (recyclerView.visibility == View.VISIBLE) {
            tipText.visibility = View.VISIBLE
            recyclerView.visibility = View.INVISIBLE
        }
    }
}