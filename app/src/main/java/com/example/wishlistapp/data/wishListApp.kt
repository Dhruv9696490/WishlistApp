package com.example.wishlistapp.data

import android.app.Application

class wishListApp: Application(){
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}