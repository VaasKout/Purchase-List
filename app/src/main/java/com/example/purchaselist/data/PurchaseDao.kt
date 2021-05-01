package com.example.purchaselist.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchaseDao {
    @Insert
    suspend fun insertItem(purchase: Purchase)

    @Delete
    suspend fun deleteItem(purchase: Purchase)

    @Update
    suspend fun updateItems(purchaseList: List<Purchase>)

    @Query("SELECT * FROM purchase_table")
    fun getAllItems(): Flow<List<Purchase>>
}