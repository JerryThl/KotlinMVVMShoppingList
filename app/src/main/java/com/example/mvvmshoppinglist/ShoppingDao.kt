package com.example.mvvmshoppinglist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//Cannot call these function in main thread, coroutines are needed
@Dao
interface ShoppingDao {
    //Update and Insert (If no such record, insert, else update)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item:ShoppingItem)

    @Delete
    suspend fun delete(item:ShoppingItem)

    //LiveData was used for easier recyclerview setup
    @Query("SELECT * FROM shopping_item")
    suspend fun getAllShoppingItem():LiveData<List<ShoppingItem>>
}