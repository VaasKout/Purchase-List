package com.example.purchaselist.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.purchaselist.data.Purchase
import com.example.purchaselist.data.PurchaseDatabase
import com.example.purchaselist.data.Repository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository(PurchaseDatabase.getDatabase(application).purchaseDao())
    val allItems = repository.allItems
    var listAllItems = mutableListOf<Purchase>()

    fun insertItem() {
        viewModelScope.launch {
            repository.updateItems(listAllItems)
            val purchase = Purchase()
            repository.insertItem(purchase)
        }
    }

    fun deleteItem(purchase: Purchase) {
        viewModelScope.launch {
            repository.updateItems(listAllItems)
            repository.deleteItem(purchase)
        }
    }

    fun updateItems() {
        viewModelScope.launch {
            repository.updateItems(listAllItems)
        }
    }
}