package com.douglas2990.marvelapi.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.douglas2990.marvelapi.Constants.BASE_URL
import com.douglas2990.marvelapi.R
import com.douglas2990.marvelapi.api.RestManager
import com.douglas2990.marvelapi.model.modelDaniel.character.CharacterModelResponse
import com.douglas2990.marvelapi.screenState.CharacterScreenState
import com.douglas2990.marvelapi.screenState.FirstScreenState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterViewModel(): ViewModel() {

    var name: String? = null
    val state: MutableLiveData<CharacterScreenState> by lazy {
        MutableLiveData<CharacterScreenState>()
    }
    init {
        state.value = CharacterScreenState.Loading
        RestManager.getEndpoints().listCharacter(name).enqueue(object : Callback<CharacterModelResponse>{
        //RestManager.provideRetrofit1().listCharacter(name).enqueue(object : Callback<CharacterModelResponse>{
            override fun onResponse(
                call: Call<CharacterModelResponse>,
                response: Response<CharacterModelResponse>
            ) {
                response.body()?.let{ body -> {
                        state.value = CharacterScreenState.Success(body.data.results)
                    }
                }
            }

            override fun onFailure(call: Call<CharacterModelResponse>, t: Throwable) {
                state.value = CharacterScreenState.Error(R.string.app_name)
            }

        }
        )
    }
}