package com.example.purchaselist.ui.activities

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.purchaselist.data.ColorResources
import com.example.purchaselist.data.Purchase
import com.example.purchaselist.data.StringResources
import com.example.purchaselist.ui.adapters.PurchaseAdapter
import com.example.purchaselist.ui.screens.MainActivityScreen
import com.example.purchaselist.ui.viewmodels.MainViewModel
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {
    private val layout by lazy { MainActivityScreen(this) }
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var purchaseAdapter: PurchaseAdapter
    private var focusPos = 0

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
            },
            textFocusCallback = { position, hasFocus ->
                if (hasFocus) {
                    focusPos = position
                }
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

        layout.recyclerView.apply {
            adapter = purchaseAdapter
            scrollToPosition(viewModel.getPositionFromPrefs())
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    val layoutManager = layout.recyclerView.layoutManager as LinearLayoutManager
                    viewModel.position = layoutManager.findFirstVisibleItemPosition()
                    if (layoutManager.findFirstVisibleItemPosition() > focusPos ||
                        layoutManager.findLastVisibleItemPosition() < focusPos
                    ) {
                        disableKeyboard()
                    }
                }
            })
        }

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
        viewModel.putPosInPrefs()
    }

    private fun disableKeyboard() {
        val view: View = currentFocus ?: layout.containerView
        (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(view.windowToken, 0)
    }
}