package com.example.purchaselist.ui.screens

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.*
import com.example.purchaselist.R
import com.example.purchaselist.data.ColorResources
import com.example.purchaselist.data.StringResources
import com.example.purchaselist.ui.custom.CustomFrame

class RecyclerItem(context: Context) {

    private val checkBoxWrapper = ContextThemeWrapper(context, R.style.CheckBoxStyle)
    private val editTextWrapper = ContextThemeWrapper(context, R.style.EditTextStyle)

    val containerView = LinearLayout(context).apply {
        layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        ).apply {
            setMargins(32, 16, 32, 16)
        }
        orientation = LinearLayout.HORIZONTAL
    }

    val checkBox = CheckBox(checkBoxWrapper).apply {
        layoutParams = LinearLayout.LayoutParams(
            0,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        ).apply {
            weight = 1f
            gravity = Gravity.CENTER
        }
    }

    val customFrame = CustomFrame(context).apply {
        layoutParams = LinearLayout.LayoutParams(
            0,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            weight = 8f
            gravity = Gravity.CENTER
            setPadding(32, 0, 32, 0)
        }
    }

    val itemEditText = EditText(editTextWrapper).apply {
        layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            setTextAppearance(R.style.TextAppearance_MaterialComponents_Body1)
        } else {
            setTextAppearance(context, R.style.TextAppearance_MaterialComponents_Body1)
        }

        imeOptions = EditorInfo.IME_ACTION_GO
        isSingleLine = true
        isElegantTextHeight = true
        hint = StringResources.enterItem
        background = null
        setHintTextColor(Color.GRAY)
        setTextColor(Color.BLACK)
    }

    val deleteIcon = ImageView(context).apply {
        layoutParams = LinearLayout.LayoutParams(
            0,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            weight = 1f
            gravity = Gravity.CENTER
        }
        setImageResource(R.drawable.ic_delete)

    }

    init {
        containerView.apply {
            addView(checkBox)
            addView(customFrame.apply {
                addView(itemEditText)
            })
            addView(deleteIcon)
        }
    }

    fun setCheckedState() {
        customFrame.setPaintColor(ColorResources.red)
        itemEditText.paintFlags = itemEditText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }

    fun setUncheckedState() {
        customFrame.setPaintColor(ColorResources.blueLight)
        itemEditText.paintFlags = itemEditText.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}