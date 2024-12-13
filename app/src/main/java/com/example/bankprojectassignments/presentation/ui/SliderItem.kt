package com.example.bankprojectassignments.presentation.ui


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.bankprojectassignments.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.bankprojectassignments.domain.model.Image
import com.example.bankprojectassignments.presentation.ui.theme.BlueLight
import com.example.bankprojectassignments.presentation.ui.theme.GrayLight


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SliderItem( image: Image) {
    GlideImage(
        modifier = Modifier
                .height(300.dp).fillMaxWidth().clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop,
        model = image.imageUrl,
        contentDescription = null,
    ){
        it.placeholder(R.drawable.gray_light).load(image.imageUrl)
    }
}

//Snapping Row With Indicator
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SliderItems(items: List<Image>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        val pagerState = rememberPagerState(pageCount = {
            items.size
        })
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSpacing = 5.dp,
            pageSize = PageSize.Fixed(LocalConfiguration.current.screenWidthDp.dp - 32.dp)
        ) { page ->
            SliderItem( image = items[page])
        }
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) BlueLight else GrayLight
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewSliderItem() {
    SliderItem(image =dummyListOfImages[0])
}


 val dummyListOfImages = listOf<Image>(
    Image(
        title = "title1",
        subTitle = "subTitle2",
        imageUrl = "https://picsum.photos/id/12/2500/1667"
    ),Image(
        title = "title2",
        subTitle = "subTitle3",
        imageUrl = "https://picsum.photos/id/12/2500/1667"
    ),Image(
        title = "title3",
        subTitle = "subTitle3",
        imageUrl = "https://picsum.photos/id/12/2500/1667"
    ))

