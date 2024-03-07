package com.douglas2990.marvelapi.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.douglas2990.marvelapi.R
import retrofit2.Callback
import com.douglas2990.marvelapi.api.RestManager
import com.douglas2990.marvelapi.model.modelDaniel.character.CharacterModelResponse
import com.douglas2990.marvelapi.screenState.DetailScreenState
import retrofit2.Call
import retrofit2.Response

//class DetailViewModel(characterId: Int) : ViewModel() {
class DetailViewModel(characterId: String) : ViewModel() {
    var name: String? = null

    val state:MutableLiveData<DetailScreenState> by lazy{
        MutableLiveData<DetailScreenState>()
    }
    init {
        state.value = DetailScreenState.Loading

        //RestManager.getEndpoints().getComics2(characterId).enqueue(object : Callback<ComicModelResponse> {
        RestManager.getEndpoints().getComics2(characterId).enqueue(object : Callback<CharacterModelResponse> {

            //override fun onResponse(call: Call<ComicModelResponse>, response: Response<ComicModelResponse>) {
            override fun onResponse(call: Call<CharacterModelResponse>, response: Response<CharacterModelResponse>) {
                response.body()?.let { body ->

                    //state.value = DetailScreenState.Success(body)
                    //state.value = DetailScreenState.Success(body.data.result)
                    state.value = DetailScreenState.Success(body.data.results)
                }?:run{state.value = DetailScreenState.Error(R.string.erro)

                }
            }


            override fun onFailure(call: Call<CharacterModelResponse>, t: Throwable) {
                state.value = DetailScreenState.Error(R.string.erro)
            }

        })
    }
}



