package com.douglas2990.marvelapi.model.modelDaniel.local

import com.douglas2990.marvelapi.model.modelDaniel.ThumbnailModel
import com.google.gson.Gson

class MarvelConverters {

    fun fromThumbnail(thumbnailModel: ThumbnailModel): String = Gson().toJson(thumbnailModel)

    fun toThumbnail(thumbnailModel: String): ThumbnailModel =
        Gson().fromJson(thumbnailModel, ThumbnailModel::class.java)
}