package com.example.wishlistapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Wish::class],
    version = 1,
    exportSchema = false
)
abstract class WishDataBase: RoomDatabase(){
    abstract fun wishDao(): WishDao
}

//@Database(
//    entities = ["shopping-table"::class.java],
//    version = 1,
//    exportSchema = false
//)
//abstract class ShoppingDataBase: RoomDatabase() {
//    abstract fun shoppingDao(): ShoppingDao
//}