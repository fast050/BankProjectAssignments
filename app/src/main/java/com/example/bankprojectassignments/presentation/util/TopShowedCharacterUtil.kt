package com.example.bankprojectassignments.presentation.util

import com.example.bankprojectassignments.domain.model.Image


fun top3CommonCharactersWithCount(
    imagesSlider: List<Image>,
    imagesScroll: List<Image>
): List<Pair<Char, Int>> {

    val textOfAllListOfSubTitle =
        (imagesScroll.map { it.subTitle } + imagesSlider.map { it.subTitle }).joinToString("")
            .filter { it != ' ' }

    return textOfAllListOfSubTitle
        .groupingBy { it } // Group characters by themselves
        .eachCount() // Count occurrences of each character
        .entries // Convert to a list of entries
        .sortedByDescending { it.value } // Sort by count in descending order
        .take(3) // Take the top 3
        .map { it.toPair() } // Convert to pairs of (character, count)

}