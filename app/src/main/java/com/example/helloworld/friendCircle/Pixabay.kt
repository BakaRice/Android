package com.example.helloworld.friendCircle

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Pixabay(
        val totalHits: Int,
        val hits: Array<PhotoItem>,
        val total: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pixabay

        if (totalHits != other.totalHits) return false
        if (!hits.contentEquals(other.hits)) return false
        if (total != other.total) return false

        return true
    }

    override fun hashCode(): Int {
        var result = totalHits
        result = 31 * result + hits.contentHashCode()
        result = 31 * result + total
        return result
    }
}


@Parcelize
data class PhotoItem(
        @SerializedName("webformatURL") val previewUrl: String,
        @SerializedName("id") val photoId: Int,
        @SerializedName("largeImageURL") val fullUrl: String,
        var strURLArray: Array<String> = arrayOf(
                "https://pixabay.com/get/57e5d6404855a414f6da8c7dda79367b1536dce555506c4870277bd0914dcd51bf_640.jpg",
                "https://pixabay.com/get/55e2d3454853ad14f6da8c7dda79367b1536dce555506c4870277bd0914dcd51bf_640.jpg",
                "https://pixabay.com/get/57e8d7414e51ab14f6da8c7dda79367b1536dce555506c4870277bd0914dcd51bf_640.jpg")
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PhotoItem

        if (previewUrl != other.previewUrl) return false
        if (photoId != other.photoId) return false
        if (fullUrl != other.fullUrl) return false
        if (!strURLArray.contentEquals(other.strURLArray)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = previewUrl.hashCode()
        result = 31 * result + photoId
        result = 31 * result + fullUrl.hashCode()
        result = 31 * result + strURLArray.contentHashCode()
        return result
    }
}

