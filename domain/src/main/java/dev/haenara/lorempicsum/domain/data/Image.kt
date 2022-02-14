package dev.haenara.lorempicsum.domain.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    @SerialName("author")
    val author: String,
    @SerialName("id")
    val id: String,
    @SerialName("url")
    val url: String,
    @SerialName("download_url")
    val downloadUrl: String,
    @SerialName("width")
    val width: Int,
    @SerialName("height")
    val height: Int
) : java.io.Serializable
