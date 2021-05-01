package com.example.purchaselist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "purchase_table")
data class Purchase(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "item_text") var itemText: String = "",
    @ColumnInfo(name = "is_checked") var isChecked: Boolean = false,
)