package com.douglas2990.marvelapi.viewModel.allComics

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.douglas2990.marvelapi.R
import com.douglas2990.marvelapi.api.RestManager
import com.douglas2990.marvelapi.model.allComics.AllComicsModel
import com.douglas2990.marvelapi.screenState.allcomics.AllComicsScreenState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllComicsViewModel: ViewModel() {

    val state: MutableLiveData<AllComicsScreenState> by lazy{
        MutableLiveData<AllComicsScreenState>()
    }
    init {
        state.value = AllComicsScreenState.Loading
        RestManager.getEndpoints().comics(100,0).enqueue(object : Callback<AllComicsModel>{
            override fun onResponse(
                call: Call<AllComicsModel>,
                response: Response<AllComicsModel>
            ) {
                response.body()?.let { body ->
                    state.value = AllComicsScreenState.Success(body.data.results)
                }
            }

            override fun onFailure(call: Call<AllComicsModel>, t: Throwable) {
                state.value = AllComicsScreenState.Error(R.string.erro)
            }


        })
    }
}