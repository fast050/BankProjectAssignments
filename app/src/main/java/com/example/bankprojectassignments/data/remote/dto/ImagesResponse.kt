package com.example.bankprojectassignments.data.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ImagesResponse(
    @SerializedName("results")
    val results: List<ImageItem>?,
    @SerializedName("total")
    val total: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?
) : Parcelable {
    @Parcelize
    data class ImageItem(
        @SerializedName("created_at")
        val createdAt: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("id")
        val id: String?,
        @SerializedName("urls")
        val urls: Urls?,
    ) : Parcelable {
        @Parcelize
        data class Urls(
            @SerializedName("full")
            val full: String?,
            @SerializedName("raw")
            val raw: String?,
            @SerializedName("regular")
            val regular: String?,
            @SerializedName("small")
            val small: String?,
            @SerializedName("thumb")
            val thumb: String?
        ) : Parcelable
    }
}
