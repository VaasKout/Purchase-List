package com.example.purchaselist.data

import android.content.res.ColorStateList

object States {

    private val stateArr = arrayOf(
        intArrayOf(android.R.attr.state_enabled)
    )

    private val blueLightColorArr = intArrayOf(
        ColorResources.blueLight
    )

    val blueLightState = ColorStateList(stateArr, blueLightColorArr)
}