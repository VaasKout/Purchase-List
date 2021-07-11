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

class MainActivityScreen(private val context: Context) {

    lateinit var mainContainerView: FrameLayout
    private lateinit var linearLayoutView: LinearLayout
    lateinit var toolbar: Toolbar
    lateinit var recyclerView: RecyclerView
    private lateinit var tipTextView: TextView
    lateinit var floatingActionButton: FloatingActionButton

    fun build(): MainActivityScreen {
        mainContainerView = FrameLayout(context).apply {
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
            )
            setBackgroundColor(Color.WHITE)
        }
        linearLayoutView = LinearLayout(context).apply {
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
            )
            orientation = LinearLayout.VERTICAL
        }

        toolbar = Toolbar(context).apply {
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

        recyclerView = RecyclerView(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
            )
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }

        tipTextView = TextView(context).apply {
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

        floatingActionButton = FloatingActionButton(context).apply {
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.BOTTOM or Gravity.END
            ).apply {
                setMargins(0, 0, 64, 64)
            }
            setImageResource(R.drawable.ic_add)
            backgroundTintList = States.blueLightState

        }

        mainContainerView.apply {
            addView(linearLayoutView.apply {
                addView(toolbar)
                addView(recyclerView)
                addView(tipTextView)
            })
            addView(floatingActionButton)
        }
        return this
    }

    fun setFullState() {
        if (recyclerView.visibility == View.GONE) {
            recyclerView.visibility = View.VISIBLE
            tipTextView.visibility = View.GONE
        }
    }

    fun setEmptyState() {
        if (recyclerView.visibility == View.VISIBLE) {
            recyclerView.visibility = View.GONE
            tipTextView.visibility = View.VISIBLE
        }
    }
}