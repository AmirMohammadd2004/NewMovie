package com.amir.newmovies.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

val selectedIndex =   mutableIntStateOf(0)

@Composable
fun NavigationBottom()  {

    NavigationItem(
        selectedIndex = selectedIndex.intValue ,
        onItemSelected = { selectedIndex.intValue = it}
    )

}

@Composable
fun NavigationItem(selectedIndex: Int, onItemSelected: (Int) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 56.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {


        IconToggleButton(
            checked = selectedIndex == 1,
            onCheckedChange = { onItemSelected.invoke(1) }

        ) {
            Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(30.dp))
        }



        IconToggleButton(
            checked = selectedIndex == 2,
            onCheckedChange = { onItemSelected.invoke(2) }
        ) {
            Icon(Icons.Default.List, contentDescription = null, modifier = Modifier.size(30.dp))
        }

        IconToggleButton(
            checked = selectedIndex == 0,
            onCheckedChange = { onItemSelected.invoke(0) }
        ) {
            Icon(Icons.Default.Home, contentDescription = null, modifier = Modifier.size(30.dp))
        }
    }
}
