package com.example.bankprojectassignments.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_list_entity")
data class ImagesEntity (
    @PrimaryKey
    val id :String,
    val title :String?,
    val subTitle:String?,
    val imageUrl :String?
)