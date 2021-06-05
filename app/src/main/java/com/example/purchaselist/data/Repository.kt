package com.example.purchaselist.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class Repository(private val purchaseDao: PurchaseDao) {

    val allItems: Flow<List<Purchase>> = purchaseDao.getAllItems()

    suspend fun insertItem(purchase: Purchase) = withContext(Dispatchers.IO){
        purchaseDao.insertItem(purchase)
    }

    suspend fun deleteItem(purchase: Purchase) = withContext(Dispatchers.IO){
        purchaseDao.deleteItem(purchase)
    }

    suspend fun deleteAllItems() = withContext(Dispatchers.IO){
        purchaseDao.deleteAllItems()
    }

    suspend fun updateItems(purchaseList: List<Purchase>) = withContext(Dispatchers.IO){
        purchaseDao.updateItems(purchaseList)
    }
}