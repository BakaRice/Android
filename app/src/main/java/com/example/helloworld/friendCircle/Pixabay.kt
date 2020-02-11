package com.example.helloworld.friendCircle

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Pixabay(
//        val totalHits: Int,
//        val hits: Array<PhotoItem>,
//        val total: Int

//自己的api
        @SerializedName("totalElements")
        val totalHits: Int,
        @SerializedName("content")
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
//        @SerializedName("webformatURL") val previewUrl: String,
//        @SerializedName("id") val photoId: Int,
//        @SerializedName("largeImageURL") val fullUrl: String,
//        @SerializedName("likes") val likeNum: String,
//        @SerializedName("user") val userName: String,
//        //tags后期还要改 临时使用一下
//        @SerializedName("tags") var title: String,
//        //用来存放多图片的list
//        var strURLArray: Array<String> = arrayOf(
//                "https://pixabay.com/get/57e5d6404855a414f6da8c7dda79367b1536dce555506c4870277bd0914dcd51bf_640.jpg",
//                "https://pixabay.com/get/55e2d3454853ad14f6da8c7dda79367b1536dce555506c4870277bd0914dcd51bf_640.jpg",
//                "https://pixabay.com/get/57e8d7414e51ab14f6da8c7dda79367b1536dce555506c4870277bd0914dcd51bf_640.jpg"),
//        //正文内容
//        var content: String,
//        //是否关注
//        var isLike: Boolean


        @SerializedName("name") val userName: String,
        @SerializedName("title") var title: String,
        @SerializedName("momentUuid") val photoId: String,
        @SerializedName("likeNum") val likeNum: String,
        //用来存放多图片的list
        @SerializedName("imgUrls") var strURLArray: Array<String>,
        val previewUrl: String = strURLArray[0],
        val fullUrl: String = strURLArray[0],
        //正文内容
        @SerializedName("content") var content: String,
        //是否关注
        var isLike: Boolean
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PhotoItem

        if (userName != other.userName) return false
        if (title != other.title) return false
        if (photoId != other.photoId) return false
        if (likeNum != other.likeNum) return false
        if (!strURLArray.contentEquals(other.strURLArray)) return false
        if (content != other.content) return false
        if (isLike != other.isLike) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userName.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + photoId.hashCode()
        result = 31 * result + likeNum.hashCode()
        result = 31 * result + strURLArray.contentHashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + isLike.hashCode()
        return result
    }
}

