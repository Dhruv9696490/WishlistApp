package com.example.wishlistapp.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
class WishViewModel(private val wishRepository: WishRepository = Graph.wishRepository): ViewModel(){
    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")
    fun onWishTitleChange(newString: String){
        wishTitleState=newString
    }
    fun onWishDescriptionChange(newString: String){
        wishDescriptionState=newString
    }
    val getAllWish = wishRepository.getAllWish()
    fun addWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO){
            wishRepository.addWish(wish)
        }
    }
    fun deleteWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO){
            wishRepository.deleteWish(wish)
        }
    }
    fun updateWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO){
            wishRepository.updateWish(wish)
        }
    }
    fun getSingleWishById(id: Long): Flow<Wish>{
        return wishRepository.getSingleWish(id)
    }
}