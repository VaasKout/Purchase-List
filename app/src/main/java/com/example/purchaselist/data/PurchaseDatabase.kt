package com.example.purchaselist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Purchase::class], version = 1, exportSchema = false)
abstract class PurchaseDatabase : RoomDatabase() {
    abstract fun purchaseDao(): PurchaseDao

    companion object {
        @Volatile
        var INSTANCE: PurchaseDatabase? = null
        fun getDatabase(context: Context): PurchaseDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context,
                    PurchaseDatabase::class.java,
                    "purchase_database"
                ).addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        CoroutineScope(Dispatchers.IO).launch {
                            getDatabase(context).purchaseDao().insertItem(Purchase())
                        }
                    }
                })
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
    }
}