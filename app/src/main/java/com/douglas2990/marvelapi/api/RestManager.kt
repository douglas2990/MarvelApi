package com.douglas2990.marvelapi.api

import com.google.gson.GsonBuilder
import androidx.viewbinding.BuildConfig
import com.douglas2990.marvelapi.Constants
import com.douglas2990.marvelapi.Constants.BASE_URL
import com.douglas2990.marvelapi.model.Comics
import com.douglas2990.marvelapi.model.Data
import com.douglas2990.marvelapi.model.Item
import com.douglas2990.marvelapi.model.MarvelModel
import com.douglas2990.marvelapi.model.allComics.AllComicsModel
import com.douglas2990.marvelapi.model.characterID.Result
import com.douglas2990.marvelapi.model.modelDaniel.character.CharacterModelResponse
import com.douglas2990.marvelapi.model.modelDaniel.comic.ComicModelResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryName
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit
import timber.log.Timber


class RestManager {
    companion object {

        fun provideOkthhptClient(): OkHttpClient{

            val loggin = HttpLoggingInterceptor()
            loggin.level = HttpLoggingInterceptor.Level.BODY

            val client =  OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor { chain ->
                    val currentTimestamp = System.currentTimeMillis()
                    val newUrl = chain.request().url
                        .newBuilder()
                        .addQueryParameter(Constants.TS, currentTimestamp.toString())
                        .addQueryParameter(Constants.APIKEY, Constants.PUBLIC_KEY)
                        .addQueryParameter(Constants.HASH,
                        provideToMd5Hash(currentTimestamp.toString() + Constants.PRIVATE_KEY + Constants.PUBLIC_KEY))
                        .build()

                    val newRequest = chain.request()
                        .newBuilder()
                        .url(newUrl)
                        .build()
                   chain.proceed(newRequest)
                }
                .addInterceptor(loggin)
                .build()

            if (BuildConfig.DEBUG){
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                client.interceptors
            }
            return client


        }

        private fun provideToMd5Hash(encrypted: String): String {

            var pass = encrypted
            var encryptedString: String? = null
            val md5: MessageDigest
            try {
                md5 = MessageDigest.getInstance("MD5")
                md5.update(pass.toByteArray(), 0, pass.length)
                pass = BigInteger(1, md5.digest()).toString(16)
                while (pass.length < 32) {
                    pass = "0$pass"
                }
                encryptedString = pass
            } catch (e1: NoSuchAlgorithmException) {
                e1.printStackTrace()
            }
            Timber.d("hash -> $encryptedString")
            return encryptedString ?: ""
        }

        private fun createHttpClient(): OkHttpClient.Builder{
            val client = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG){
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                client.addInterceptor(loggingInterceptor)
            }
            return client
        }

        fun getEndpoints(): IEndpoints{
            val gson = GsonBuilder().setLenient().create()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://gateway.marvel.com/v1/public/")
                .addConverterFactory(GsonConverterFactory.create())
                 .client(provideOkthhptClient().newBuilder().build())
                .build()
            return retrofit.create(IEndpoints::class.java)
        }

        fun provideRetrofit(): Retrofit{
            val client: OkHttpClient
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(provideOkthhptClient())
                .build()
        }

        fun provideRetrofit1(): IEndpoints{

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(provideOkthhptClient().newBuilder().build())
                .build()
            return retrofit.create(IEndpoints::class.java)
        }



        fun provideServiceApi(retrofit: Retrofit): ServiceApi{
            return retrofit.create(ServiceApi::class.java)
        }

        fun provideServiceApi1(retrofit: Retrofit): IEndpoints{
            return retrofit.create(IEndpoints::class.java)
        }

        interface IEndpoints{
            /*
           @GET("characters")
            //fun comics():Call<Data>
            fun firstMarvelApi():Call<MarvelModel>
            //fun comics():Call<Comics>

             */

            @GET("characters")
            fun listCharacter(
                @Query("nameStartsWith") nameStartWith: String? = null
            ): Call<CharacterModelResponse>

            @GET("characters/{characterId}?")
            fun idCharacter(
                //@Query("nameStartsWith") nameStartWith: String? = null,
                @Path("characterId") id : String? = null,
                //@Path("characterId", encoded = true) id : Int,
            ): Call<Result>

            @GET("characters/{characterId}?")
            fun idCharacter2(
                //@Query("nameStartsWith") nameStartWith: String? = null,
                @Path("characterId") id : String? = null,
                //@Path("characterId", encoded = true) id : Int,
            ): Call<ComicModelResponse>

            @GET("characters/{characterId}/comics")
            suspend fun getComics(
                @Path(
                    value = "characterId",
                    encoded = true
                ) characterId: Int
            ) : Response<ComicModelResponse>

            // @GET("characters/{characterId}/comics")
            @GET("characters/{characterId}")
            fun getComics2(
                @Path(
                    value = "characterId",
                    encoded = true
                ) characterId: String
            //) : Call<ComicModelResponse>
             ): Call<CharacterModelResponse>



            /////  A PARTIR DO DIA 25/10/2023

            @GET("comics")
            fun comics(
                @Query("limit")limit: Int = 100,
                @Query("offset")offset: Int = 0
            ):Call<AllComicsModel>

            @GET("characters")
            fun firstMarvelApi(
                @Query("limit")limit: Int = 100,
                @Query("offset")offset: Int = 0
            ):Call<MarvelModel>

        }

    }
}