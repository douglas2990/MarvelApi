package com.douglas2990.marvelapi.api

import com.douglas2990.marvelapi.model.MarvelModel
import com.douglas2990.marvelapi.model.modelDaniel.character.CharacterModelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {

    @GET("characters")
    suspend fun list(
        @Query("nameStartsWith") nameStartsWith: String? = null
    ): Response<CharacterModelResponse>

    @GET("characters/{characterId}/comics")
    suspend fun getComics(
        @Path(
            value = "characterId",
            encoded = true
        ) characterId: Int
    ): Response<CharacterModelResponse>


}