package com.example.purchaselist.ui.viewmodels

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.purchaselist.data.Purchase
import com.example.purchaselist.data.PurchaseDatabase
import com.example.purchaselist.data.Repository
import com.example.purchaselist.data.StringResources
import com.example.purchaselist.data.StringResources.TAG_LAST_POS
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository(PurchaseDatabase.getDatabase(application).purchaseDao())
    private val prefs: SharedPreferences =
        application.getSharedPreferences(StringResources.prefs, Context.MODE_PRIVATE)

    val allItems = repository.allItems
    var listAllItems = mutableListOf<Purchase>()
    var position = 0


    init {
        prefs.getBoolean(StringResources.TAG_FIRST_LOAD, true).also {
            if (it) {
                viewModelScope.launch {
                    repository.insertItem(Purchase())
                }
                prefs.edit {
                    putBoolean(StringResources.TAG_FIRST_LOAD, false)
                }
            }
        }
    }

    fun insertItem() {
        viewModelScope.launch {
            repository.updateItems(listAllItems)
            val purchase = Purchase()
            repository.insertItem(purchase)
        }
    }

    fun deleteAllItems(){
        viewModelScope.launch {
            repository.deleteAllItems()
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

    fun putPosInPrefs() {
        prefs.edit {
            putInt(TAG_LAST_POS, position)
        }
    }

    fun getPositionFromPrefs(): Int = prefs.getInt(TAG_LAST_POS, 0)

//    scrollToPosition(prefs.getInt(POS, 0))
}