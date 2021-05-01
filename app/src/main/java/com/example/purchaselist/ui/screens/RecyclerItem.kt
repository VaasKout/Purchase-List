package com.example.purchaselist.ui.screens

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.text.InputType
import android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE
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
            setPadding(32, 8, 32, 8)
        }
    }

    val editItemText = EditText(editTextWrapper).apply {
        layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            setTextAppearance(R.style.TextAppearance_MaterialComponents_Headline6)
        } else {
            setTextAppearance(context, R.style.TextAppearance_MaterialComponents_Headline6)
        }
        setTextColor(Color.BLACK)
        inputType =
            InputType.TYPE_TEXT_FLAG_IME_MULTI_LINE or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
        imeOptions = EditorInfo.IME_ACTION_GO
        isSingleLine = false
        isElegantTextHeight = true
        hint = StringResources.enterItem
        background = null
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
                addView(editItemText)
            })
            addView(deleteIcon)
        }
    }

    fun setCheckedState(){
        customFrame.setPaintColor(ColorResources.red)
        editItemText.paintFlags = editItemText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }

    fun setUncheckedState(){
        customFrame.setPaintColor(ColorResources.blueLight)
        editItemText.paintFlags = editItemText.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}