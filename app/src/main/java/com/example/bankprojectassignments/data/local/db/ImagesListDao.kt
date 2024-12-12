package com.example.bankprojectassignments.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.example.bankprojectassignments.data.local.entity.ImagesEntity

@Dao
interface ImagesListDao {
    @Insert(onConflict = IGNORE)
    suspend fun insertImages(news: List<ImagesEntity>)

    @Query("delete from image_list_entity")
    suspend fun clearCache()

    @Query("select * from image_list_entity")
    suspend fun getCacheImages(): List<ImagesEntity>
}