package com.example.bankprojectassignments.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    onSearchTextChange: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var searchQuery by remember { mutableStateOf("") }

    TextField(
        value = searchQuery,
        onValueChange = { searchQuery = it },
        placeholder = { Text("Search...") },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search // Explicitly set the IME action to "Search"
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                // Triggered when the search key is pressed
                onSearchTextChange(searchQuery) // Emit the search query
                keyboardController?.hide() // Hide the keyboard after search
            }
        ),
        singleLine = true
        ,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(20.dp))
    )
}

@Composable
fun SearchScreen() {
    // Use remember and mutableStateOf with property delegation (`by`)
    var searchQuery by remember { mutableStateOf("") }
    var searchResult by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        SearchBar(
            onSearchTextChange = { query ->
                searchQuery = query // Update search query
                searchResult = "You searched for: $query" // Simulate search result
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = searchResult,
            style = MaterialTheme.typography.body1
        )
    }
}


@Preview
@Composable
fun PreviewSearchBar(){
    SearchBar() {

    }
}



