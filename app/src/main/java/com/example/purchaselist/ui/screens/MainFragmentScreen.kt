package com.example.purchaselist.ui.screens

import android.content.Context
import android.graphics.Color
import android.view.*
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
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
        } else{
            setTextAppearance(context, R.style.TextAppearance_MaterialComponents_Headline6)
        }
        setTextColor(Color.GRAY)
        visibility = View.GONE
    }

    init {
        containerView.apply {
            addView(toolbar)
            addView(recyclerView)
            addView(tipText)
        }
    }

    fun setFullState(){
        recyclerView.visibility = View.VISIBLE
        tipText.visibility = View.GONE
    }

    fun setEmptyState(){
        recyclerView.visibility = View.GONE
        tipText.visibility = View.VISIBLE
    }
}