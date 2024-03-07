package com.douglas2990.marvelapi.model.modelDaniel.character

import com.douglas2990.marvelapi.model.modelDaniel.ThumbnailModel
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CharacterModel(

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailModel
) : Serializable
