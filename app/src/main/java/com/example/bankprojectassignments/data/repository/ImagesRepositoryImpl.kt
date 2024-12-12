package com.example.bankprojectassignments.data.repository

import android.util.Log
import com.example.bankprojectassignments.common.Resources
import com.example.bankprojectassignments.data.local.db.ImagesListDao
import com.example.bankprojectassignments.data.mapper.toImageList
import com.example.bankprojectassignments.data.mapper.toImagesEntity
import com.example.bankprojectassignments.data.remote.ImagesAPI
import com.example.bankprojectassignments.domain.model.Image
import com.example.bankprojectassignments.domain.repository.ImagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ImagesRepositoryImpl @Inject constructor(
    private val imagesAPI: ImagesAPI,
    private val imagesListDao: ImagesListDao
) : ImagesRepository {
    override fun getImages(query: String, itemNumber :Int?): Flow<Resources<List<Image>>> = flow {
        emit(Resources.Loading())

        val cacheImageList = imagesListDao.getCacheImages().map { it.toImageList() }
        emit(Resources.Loading(data = cacheImageList))

        try {
            val remoteImagesList = imagesAPI.getPhotos(query = query , per_page = itemNumber?:10)

            Log.d("TAG", "getImages: $remoteImagesList")
            imagesListDao.clearCache()
            remoteImagesList.results?.map { it.toImagesEntity() }?.let {
                imagesListDao.insertImages(it)
            }

            val imageList = imagesListDao.getCacheImages().map { it.toImageList()}
            emit(Resources.Success(imageList))

        } catch (e: HttpException) {
            emit(
                Resources.Error(
                    message = e.message ?: "Opps! something went wrong",
                    data = cacheImageList
                )
            )
        } catch (e: IOException) {
            emit(
                Resources.Error(
                    message = e.message ?: "Check your internet connection",
                    data = cacheImageList
                )
            )
        }

    }


}