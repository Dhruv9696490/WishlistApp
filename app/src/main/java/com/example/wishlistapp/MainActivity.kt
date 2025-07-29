package com.example.wishlistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wishlistapp.data.Route
import com.example.wishlistapp.data.Wish
import com.example.wishlistapp.data.WishViewModel
import com.example.wishlistapp.ui.theme.WishlistAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WishlistAppTheme {
                Navigation()
            }
        }
    }
}
@Composable
fun WishlistScreen(navController: NavController,viewModel: WishViewModel){
    val wishList=viewModel.getAllWish.collectAsState(initial = emptyList()).value
    Scaffold(
        topBar = { TopAppBarScreen("Wishlist App") },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(route = Route.AddScreen.route + "/0L")
            },
                contentColor = Color.White,
                modifier = Modifier.padding(all = 20.dp),
                backgroundColor = Color.Black){
                Icon(Icons.Default.Add,contentDescription = null)
            }
        }
    ){ padding->
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(padding)){
            items(wishList){wish->
                WishListItem(wish, onCardClick = {
                    val id=wish.id
                    navController.navigate(route = Route.AddScreen.route + "/$id")
                })
            }
        }
    }
}
@Composable
fun WishListItem(wish: Wish, onCardClick:()->Unit){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp, start = 8.dp, end = 8.dp)
        .clickable{
            onCardClick()
        },
        elevation = 15.dp,
        backgroundColor = Color.White
        ){
    Column(modifier = Modifier.padding(16.dp)) {
        Text(wish.title, fontWeight = FontWeight.ExtraBold)
        Text(wish.description)
    }
}
}