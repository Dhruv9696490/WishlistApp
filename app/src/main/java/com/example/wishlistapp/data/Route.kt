package com.example.wishlistapp.data

sealed class Route(val route: String){
   object HomeScreen: Route("home_Screen")
    object AddScreen: Route("Add_Screen")
}