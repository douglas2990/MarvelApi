package com.douglas2990.marvelapi.model.modelDaniel

import com.google.gson.annotations.SerializedName
import java.security.cert.Extension

data class ThumbnailModel(
    @SerializedName("path")
    val path: String,
    @SerializedName("extension")
    val extension: String
)
