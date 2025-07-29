package com.example.wishlistapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.wishlistapp.data.Route
import com.example.wishlistapp.data.Route.HomeScreen
import com.example.wishlistapp.data.WishViewModel

@Composable
fun Navigation(){
    val navController = rememberNavController()
    val context= LocalContext.current
    val viewModel: WishViewModel = viewModel()
    NavHost(navController, HomeScreen.route){
        composable(HomeScreen.route){
            WishlistScreen(navController,viewModel)
        }
        composable(Route.AddScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.LongType
                    defaultValue=0L
                    nullable=false
                }
            )){entry->
            val id= if(entry.arguments!=null) entry.arguments!!.getLong("id") else 0L
            AddEditScreen(id=id, viewModel = viewModel,navController)
        }
    }
}