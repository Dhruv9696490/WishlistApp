package com.example.wishlistapp.data

import kotlinx.coroutines.flow.Flow

class WishRepositry(private val wishDao: WishDao){
    suspend fun addWish(wish: Wish)=wishDao.addWish(wish)
    fun getAllWish(): Flow<List<Wish>>{
        return wishDao.getAllWish()
    }
    suspend fun updateWish(wish: Wish)= wishDao.updateWish(wish)
    suspend fun deleteWish(wish: Wish)=  wishDao.delete(wish)

    fun getSingleWish(id: Long) : Flow<Wish>{
        return wishDao.getSingleWish(id)
    }
}
