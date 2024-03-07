package com.douglas2990.marvelapi.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.douglas2990.marvelapi.R
import com.douglas2990.marvelapi.api.RestManager
import com.douglas2990.marvelapi.model.MarvelModel
import com.douglas2990.marvelapi.screenState.FirstScreenState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarvelViewModel: ViewModel() {

    val state: MutableLiveData<FirstScreenState> by lazy {
        MutableLiveData<FirstScreenState>()
    }
    init {
        state.value = FirstScreenState.Loading
        //RestManager.getEndpoints().comics().enqueue(object : Callback<Comics>{
        //RestManager.getEndpoints().comics().enqueue(object : Callback<Data>{
        RestManager.getEndpoints().firstMarvelApi(100,0).enqueue(object : Callback<MarvelModel>{
            override fun onResponse(
                //call: Call<Comics>,
                //call: Call<Data>,
                call: Call<MarvelModel>,
                //response: Response<Comics>)
                //response: Response<Data>)
                response: Response<MarvelModel>)
            {
                response.body()?.let { body ->
                    //state.value = FirstScreenState.Success(body.items)
                    //state.value = FirstScreenState.Success(body.results)
                    state.value = FirstScreenState.Success(body.data.results)
                }
            }

            //override fun onFailure(call: Call<Comics>, t: Throwable) {
            //override fun onFailure(call: Call<Data>, t: Throwable) {
            override fun onFailure(call: Call<MarvelModel>, t: Throwable) {
                state.value = FirstScreenState.Error(R.string.erro)
            }

        })
    }
}