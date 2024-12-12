package com.example.bankprojectassignments.presentation.util

import com.example.bankprojectassignments.R
import android.app.Activity
import android.view.View
import android.widget.TextView
import com.example.bankprojectassignments.domain.model.Image
import com.google.android.material.bottomsheet.BottomSheetDialog

fun Activity.showBottomSheetDialog(imagesSlider: List<Image>, imagesScroll: List<Image>) {
    val bottomSheetDialog = BottomSheetDialog(this)

    // Inflate a layout for the BottomSheet (can be a simple layout with TextViews)
    val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_info, null)
    bottomSheetDialog.setContentView(bottomSheetView)

    // Get statistics data and update the UI
    displayStatistics(bottomSheetView, imagesSlider, imagesScroll)

    // Show the BottomSheet
    bottomSheetDialog.show()
}

private fun displayStatistics(view: View, imagesSlider: List<Image>, imagesScroll: List<Image>) {
    val itemCountTextView = view.findViewById<TextView>(R.id.item_count)
    val topCharactersTextView = view.findViewById<TextView>(R.id.top_characters)

    // Set item count text
    itemCountTextView.text = "Items Count: ${imagesScroll.size + imagesSlider.size}"

    val textOfAllListOfSubTitle =
        (imagesScroll.map { it.subTitle } + imagesSlider.map { it.subTitle }).joinToString("").filter{it!=' '}

    // Set top 3 characters
    val topChars = top3CommonCharactersWithCount(textOfAllListOfSubTitle).joinToString("\n") {
        "${it.first} = ${it.second}"
    }
    topCharactersTextView.text = "Top 3 Characters:\n$topChars"
}

fun top3CommonCharactersWithCount(input: String): List<Pair<Char, Int>> {
    return input
        .groupingBy { it } // Group characters by themselves
        .eachCount() // Count occurrences of each character
        .entries // Convert to a list of entries
        .sortedByDescending { it.value } // Sort by count in descending order
        .take(3) // Take the top 3
        .map { it.toPair() } // Convert to pairs of (character, count)
}