package com.example.wishlistapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishDao{
    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    abstract suspend fun addWish(wishEntity: Wish)
    @Query("Select * from `wish-table`")
    abstract fun getAllWish(): Flow<List<Wish>>
    @Update
    abstract suspend fun updateWish(wishEntity: Wish)
    @Delete
    abstract suspend fun delete(wishEntity: Wish)
    @Query("Select * from `wish-table` where id=:id")
    abstract fun getSingleWish(id: Long): Flow<Wish>
}