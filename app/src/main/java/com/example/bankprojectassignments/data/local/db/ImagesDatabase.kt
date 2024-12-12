package com.example.bankprojectassignments.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bankprojectassignments.data.local.entity.ImagesEntity


@Database(entities = [ImagesEntity::class] , version = 1)
abstract class ImagesDatabase : RoomDatabase() {
    abstract fun imagesDao(): ImagesListDao
    companion object{
        const val DATABASE_NAME ="imageList.db"
    }
}