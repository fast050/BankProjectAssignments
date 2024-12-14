package com.example.bankprojectassignments.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import com.example.bankprojectassignments.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.bankprojectassignments.domain.model.Image
import java.nio.file.WatchEvent

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ScrollItem(image: Image) {
    Row(
        modifier = Modifier
            .height(80.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = androidx.compose.ui.graphics.Color.LightGray)
    ) {
        GlideImage(
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop,
            model = image.imageUrl,
            contentDescription = null,
        ) {
            it.placeholder(R.drawable.gray_light).load(image.imageUrl)
        }

        Spacer(modifier = Modifier.width(5.dp))

        Column(modifier = Modifier.weight(1f) , verticalArrangement = Arrangement.Center) {
            image.title?.let { title ->
                Text(title, maxLines = 1 , modifier = Modifier.padding(top = 5.dp))
            }
            image.subTitle?.let { subTitle ->
                Text(subTitle, maxLines = 1)
            }
        }
    }
}

@Composable
fun ScrollItems(images: List<Image>) {
    LazyColumn(
        modifier =
        Modifier
            .height((80 * images.size).dp)
            .padding(vertical = 8.dp), // Padding for the entire list
        verticalArrangement = Arrangement.spacedBy(4.dp), // Space between items,
        userScrollEnabled = false
    ) {
        items(images.size) { index ->
            ScrollItem(image = images[index])
        }
    }
}

