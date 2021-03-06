package com.example.purchaselist.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.purchaselist.data.Purchase
import com.example.purchaselist.ui.screens.RecyclerItem

class PurchaseAdapter(
    val textCallback: (Int, String) -> Unit,
    val checkCallback: (Int, Boolean) -> Unit,
    val deleteCallback: (Int) -> Unit,
    val textFocusCallback: (Int, Boolean) -> Unit,
) : ListAdapter<Purchase, PurchaseAdapter.ViewHolder>(DiffUtilCallback()) {

    inner class ViewHolder(rootView: View, private val viewInstance: RecyclerItem) :
        RecyclerView.ViewHolder(rootView) {
        fun bind(purchase: Purchase) {

            viewInstance.itemEditText.setText(purchase.itemText)
            viewInstance.itemEditText.addTextChangedListener {
                textCallback(absoluteAdapterPosition, it.toString())
            }
            viewInstance.itemEditText.setOnFocusChangeListener { _, hasFocus ->
                textFocusCallback(absoluteAdapterPosition, hasFocus)
            }

            if (purchase.isChecked) {
                viewInstance.setCustomFrameCheckedState()
                viewInstance.checkBox.isChecked = true
            } else {
                viewInstance.setCustomFrameUncheckedState()
                viewInstance.checkBox.isChecked = false
            }

            viewInstance.checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewInstance.setCustomFrameCheckedState()
                } else {
                    viewInstance.setCustomFrameUncheckedState()
                }
                checkCallback(absoluteAdapterPosition, isChecked)
            }
            viewInstance.deleteIcon.setOnClickListener {
                deleteCallback(absoluteAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = RecyclerItem(parent.context).build()
        return ViewHolder(layout.containerView, layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<Purchase>() {
    override fun areItemsTheSame(oldItem: Purchase, newItem: Purchase): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Purchase, newItem: Purchase): Boolean {
        return oldItem == newItem
    }

}