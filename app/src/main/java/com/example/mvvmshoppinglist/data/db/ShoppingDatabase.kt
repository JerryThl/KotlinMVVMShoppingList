package com.example.mvvmshoppinglist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmshoppinglist.data.db.entities.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDatabase : RoomDatabase() {

    abstract fun getShoppingDao(): ShoppingDao

    //this create STATIC keyword
    companion object {
        //volatile means right to this instance will be made instantly to other threads
        @Volatile
        private var instance: ShoppingDatabase? = null
        private val LOCK = Any()

        //this function executed whenever shoppingdatabase instance was created
        operator fun invoke(context: Context): ShoppingDatabase {

            // synchronized make sure no other thread will access this instance at the same time
            return instance ?: synchronized(LOCK){
                instance ?: createDatabase(context).also { instance = it }
            }
        }

        private fun createDatabase(context: Context): ShoppingDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                ShoppingDatabase::class.java,
                "ShoppingDB.db"
            ).build()
        }
    }

}