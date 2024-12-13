package com.example.bankprojectassignments.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bankprojectassignments.domain.model.Image
import com.example.bankprojectassignments.presentation.ImagesViewModel
import com.example.bankprojectassignments.presentation.ui.theme.GrayLight
import com.example.bankprojectassignments.presentation.ui.theme.While
import com.example.bankprojectassignments.presentation.util.top3CommonCharactersWithCount


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(showBottomSheet: Boolean, viewModel: ImagesViewModel) {
    // Using BottomSheetScaffold to show the sliding sheet from the bottom

    val scrollImagesState = viewModel.imagesScroll.observeAsState()
    val sliderImagesState = viewModel.imagesSlider.observeAsState()

    val topCharactersPair = top3CommonCharactersWithCount(
        scrollImagesState.value?.data ?: emptyList(),
        sliderImagesState.value?.data ?: emptyList()
    )

    ModalBottomSheetLayout(
        sheetState = rememberModalBottomSheetState(
            if (showBottomSheet) ModalBottomSheetValue.Expanded else ModalBottomSheetValue.Hidden
        ),
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Top 3 Occurrence Characters:", style = MaterialTheme.typography.h6)

                topCharactersPair.forEach { character ->
                    Text("${character.first} = ${character.second}")
                }
            }
        },
        sheetBackgroundColor = While
    ) {
        // Empty content because bottom sheet takes up the space
    }
}