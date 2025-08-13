package com.example.wishlistapp

import android.os.Bundle
import android.util.Log.i
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Scaffold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
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
@OptIn(ExperimentalMaterialApi::class)
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
            items(wishList, key = {wish->wish.id}){wish->

                val dismissState = rememberDismissState(
                    confirmStateChange = {
                        if(it==DismissValue.DismissedToEnd|| it== DismissValue.DismissedToStart){
                            viewModel.deleteWish(wish)
                        }
                        true
                    }
                )

                SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(DismissDirection.StartToEnd),
                    dismissContent = {
                        WishListItem(wish, onCardClick = {
                            val id=wish.id
                            navController.navigate(route = Route.AddScreen.route + "/$id")
                        })
                    },
                    dismissThresholds = {
                        FractionalThreshold(0.25f)
                    },
                    background = {
                         val color by animateColorAsState(
                             if(dismissState.dismissDirection== DismissDirection.StartToEnd) Color.Red else Color.Transparent
                         )
                        val alignment = Alignment.CenterEnd
                        Box(
                            modifier = Modifier.fillMaxSize().background(color).padding(horizontal = 20.dp),
                            contentAlignment = Alignment.CenterStart
                        ){
                            Icon(Icons.Default.Delete,null, tint = Color.White)
                        }
                    }
                )


            }
        }
    }
}
@Composable
fun WishListItem(wish: Wish, onCardClick:()->Unit){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp, start = 8.dp, end = 8.dp)
        .clickable {
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