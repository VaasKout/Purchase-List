package com.example.purchaselist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.purchaselist.data.Purchase
import com.example.purchaselist.data.StringResources
import com.example.purchaselist.ui.adapters.PurchaseAdapter
import com.example.purchaselist.ui.screens.MainFragmentScreen
import com.example.purchaselist.ui.viewmodels.MainViewModel
import kotlinx.coroutines.flow.collect

class MainFragment : Fragment() {
    private val layout by lazy { MainFragmentScreen(requireContext()) }
    private val activityViewModel by viewModels<MainViewModel>()
    private lateinit var purchaseAdapter: PurchaseAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        purchaseAdapter = PurchaseAdapter(
            textCallback = { position, text ->
                activityViewModel.listAllItems[position].itemText = text
            },
            checkCallback = { position, isChecked ->
                activityViewModel.listAllItems[position].isChecked = isChecked
            },
            deleteCallback = {
                activityViewModel.deleteItem(activityViewModel.listAllItems[it])
            }
        )

        lifecycleScope.launchWhenStarted {
            activityViewModel.allItems.collect {
                purchaseAdapter.submitList(it)
                activityViewModel.listAllItems = it as MutableList<Purchase>
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
                    activityViewModel.insertItem()
                    true
                }
                else -> false
            }
        }
        return layout.containerView
    }

    override fun onPause() {
        super.onPause()
        activityViewModel.updateItems()
    }
}