package com.example.wishlistapp

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wishlistapp.data.Wish
import com.example.wishlistapp.data.WishViewModel


@Composable
fun AddEditScreen(id:Long, viewModel: WishViewModel, navController: NavController){
    val context=LocalContext.current
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    if(id!=0L){
        val wish = viewModel.getSingleWishById(id).collectAsState(initial = Wish(0L,"",""))
        viewModel.wishTitleState=wish.value.title
        viewModel.wishDescriptionState=wish.value.description
    }else{
        viewModel.wishTitleState=""
        viewModel.wishDescriptionState=""
    }
    Scaffold(
        topBar = { TopAppBarScreen(name = if(id!=0L) "Update Wish" else "Add Wish",
            onNavigationArrowClick = {
                navController.navigateUp()
            })},
        scaffoldState=scaffoldState
    ) {
        Column(modifier = Modifier.wrapContentSize().padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(label = "Title", value = viewModel.wishTitleState, onValueChanged = {
                viewModel.onWishTitleChange(it)
            })
            WishTextField(label = "Description", value = viewModel.wishDescriptionState, onValueChanged = {
                viewModel.onWishDescriptionChange(it)
            })
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                if(viewModel.wishTitleState.isNotEmpty() && viewModel.wishDescriptionState.isNotEmpty()){
                    if(id==0L){
                        viewModel.addWish(Wish(0L,viewModel.wishTitleState.trim(),viewModel.wishDescriptionState.trim()))
                        Toast.makeText(context,"Your new wish is created", Toast.LENGTH_LONG).show()
                    }else{
                        viewModel.updateWish(wish =
                            Wish(
                                id=id,
                                title = viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim()
                            ))
                        Toast.makeText(context,"Your wish is updated", Toast.LENGTH_LONG).show()
                    }
                    Toast.makeText(context,"Your wish is created", Toast.LENGTH_LONG).show()
                    navController.navigateUp()
                }else{
                   Toast.makeText(context,"Fill the above text field", Toast.LENGTH_LONG).show()
                }

            }){
                Text(
                    text = if(id!=0L) "Update Wish" else "Add Wish",
                    style = TextStyle(
                        fontSize = 18.sp
                    )
                )
            }
        }
    }
}
@Composable
fun WishTextField(
    label: String,
    value: String,
    onValueChanged: (String)-> Unit
){
    OutlinedTextField(
        value=value,
        onValueChange = onValueChanged,
        label= {Text(text = label, color = Color.Black)},
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text),
            colors= TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                focusedBorderColor = colorResource(id=R.color.black),
                unfocusedLabelColor =  colorResource(id=R.color.black),
                focusedLabelColor =  colorResource(id=R.color.black),
                unfocusedBorderColor =  colorResource(id=R.color.black),
                cursorColor =  colorResource(id=R.color.black)
            )
        )
}
