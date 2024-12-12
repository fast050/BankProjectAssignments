package com.example.bankprojectassignments.di

import android.content.Context
import androidx.room.Room
import com.example.bankprojectassignments.data.local.db.ImagesListDao
import com.example.bankprojectassignments.data.local.db.ImagesDatabase
import com.example.bankprojectassignments.data.remote.ImagesAPI
import com.example.bankprojectassignments.data.repository.ImagesRepositoryImpl
import com.example.bankprojectassignments.domain.repository.ImagesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideImagesDataBase(@ApplicationContext context: Context) : ImagesDatabase =
        Room.databaseBuilder(
            context,
            ImagesDatabase::class.java,
            ImagesDatabase.DATABASE_NAME
        ).build()

    @Provides
    fun provideImagesDao(imagesDatabase: ImagesDatabase) =
        imagesDatabase.imagesDao()

    @Provides
    @Singleton
    fun provideImagesAPI(): ImagesAPI =
        Retrofit.Builder().baseUrl(ImagesAPI.baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ImagesAPI::class.java)

    @Provides
    @Singleton
    fun provideImagesRepository(
        imagesAPI: ImagesAPI,
        imagesListDao: ImagesListDao
    ): ImagesRepository = ImagesRepositoryImpl(imagesAPI , imagesListDao)


}