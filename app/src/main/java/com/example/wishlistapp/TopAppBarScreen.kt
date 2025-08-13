package com.example.wishlistapp

import android.util.Log.e
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarScreen(name: String,onNavigationArrowClick:()->Unit={}){
        val navigationIcon: @Composable (()->Unit) = {
            if(!name.contains("Wishlist App")){
            IconButton(onClick = { onNavigationArrowClick() }) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }else{
            null
        }
    }
//    : @Composable (() -> Unit)?
        TopAppBar(title={ Text(name,color = colorResource(R.color.white),
            modifier = Modifier
                .padding(start = 4.dp)
                .heightIn(max = 24.dp)) },
            elevation = 4.dp,
            backgroundColor = colorResource(id=R.color.pink_hai),
            modifier = Modifier.padding(top = 40.dp),
            navigationIcon = navigationIcon
    )
}