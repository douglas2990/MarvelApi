package com.douglas2990.marvelapi.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//class DetailFactory(private val characterId: Int): ViewModelProvider.Factory {
class DetailFactory(private val characterId: String): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(characterId) as T
    }

}