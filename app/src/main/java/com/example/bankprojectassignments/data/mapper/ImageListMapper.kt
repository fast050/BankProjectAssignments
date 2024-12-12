package com.example.bankprojectassignments.data.mapper

import com.example.bankprojectassignments.data.local.entity.ImagesEntity
import com.example.bankprojectassignments.data.remote.dto.ImagesResponse
import com.example.bankprojectassignments.domain.model.Image

//from remote to local
fun ImagesResponse.ImageItem.toImagesEntity() : ImagesEntity =
    ImagesEntity(
        id = id ?: "NotFound",
        title = createdAt,
        subTitle = description?.take(15),
        imageUrl = urls?.full
    )


fun ImagesEntity.toImageList() : Image =
    Image(
        title = title,
        subTitle = subTitle,
        imageUrl = imageUrl
    )