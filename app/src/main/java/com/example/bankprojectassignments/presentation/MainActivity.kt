package com.example.bankprojectassignments.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bankprojectassignments.presentation.ui.BottomSheet
import com.example.bankprojectassignments.presentation.ui.ScrollItems
import com.example.bankprojectassignments.presentation.ui.SearchBar
import com.example.bankprojectassignments.presentation.ui.SliderItems
import com.example.bankprojectassignments.presentation.ui.theme.BankProjectAssignmentTheme
import com.example.bankprojectassignments.presentation.ui.theme.BlueLight
import com.example.bankprojectassignments.presentation.ui.theme.While
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<ImagesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getImagesSlider("dog")
        viewModel.getImagesScroll("cat")


        setContent {
            BankProjectAssignmentTheme {
                var showBottomSheet by remember { mutableStateOf(false) }

                // State for bottom sheet content (e.g., top 3 characters)

                Scaffold(modifier = Modifier.fillMaxSize().padding(top = 20.dp),
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { showBottomSheet = !showBottomSheet },
                            backgroundColor = BlueLight
                        ) {
                            Icon(Icons.Default.Add, contentDescription = "Add")
                        }
                    },
                    content = { innerPadding ->
                        Box(
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                        ) {
                            MainScreen()

                            // Bottom sheet slider
                            BottomSheet(showBottomSheet = showBottomSheet, viewModel = viewModel)
                        }
                    }
                )
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun MainScreen(){
        val imagesForSlider = viewModel.imagesSlider.observeAsState()
        val imagesForScroll = viewModel.imagesScroll.observeAsState()

        LazyColumn(modifier = Modifier.heightIn(800.dp)) {
            
            item{
                imagesForSlider.value?.data?.let {
                    SliderItems(it)
                }
            }
            stickyHeader{
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(While) // Set a solid background color for the header
                ) {
                    SearchBar(
                        onSearchTextChange = { query ->
                            viewModel.getImagesScroll(query)
                        }
                    )
                }
            }
            item{
                imagesForScroll.value?.data?.let {
                    ScrollItems(images = it)
                }
            }


        }
    }
}