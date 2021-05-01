package com.example.purchaselist.ui.activities

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.purchaselist.data.ColorResources
import com.example.purchaselist.data.Purchase
import com.example.purchaselist.data.StringResources
import com.example.purchaselist.ui.adapters.PurchaseAdapter
import com.example.purchaselist.ui.screens.MainActivityScreen
import com.example.purchaselist.ui.viewmodels.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {
    private val layout by lazy { MainActivityScreen(this) }
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var purchaseAdapter: PurchaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ColorResources.blueDark
        setContentView(layout.containerView)
        purchaseAdapter = PurchaseAdapter(
            textCallback = { position, text ->
                viewModel.listAllItems[position].itemText = text
            },
            checkCallback = { position, isChecked ->
                viewModel.listAllItems[position].isChecked = isChecked
            },
            deleteCallback = {
                viewModel.deleteItem(viewModel.listAllItems[it])
            }
        )

        lifecycleScope.launchWhenStarted {
            viewModel.allItems.collect {
                purchaseAdapter.submitList(it)
                viewModel.listAllItems = it as MutableList<Purchase>
                if (it.isEmpty()) {
                    layout.setEmptyState()
                } else {
                    layout.setFullState()
                }
            }
        }
        layout.recyclerView.adapter = purchaseAdapter


        layout.toolbar.setOnMenuItemClickListener {
            when (it.title) {
                StringResources.addItem -> {
                    viewModel.insertItem()
                    true
                }
                else -> false
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.updateItems()
    }
}