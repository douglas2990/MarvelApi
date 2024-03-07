package com.douglas2990.marvelapi.model.modelDaniel.character

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CharacterModelData(
    @SerializedName("results")
    val results: List<CharacterModel>
): Serializable
