package com.example.bankprojectassignments.domain.repository

import com.example.bankprojectassignments.common.Resources
import com.example.bankprojectassignments.domain.model.Image
import kotlinx.coroutines.flow.Flow

interface ImagesRepository {
    fun getImages(query:String, itemNumber :Int?) : Flow<Resources<List<Image>>>
}