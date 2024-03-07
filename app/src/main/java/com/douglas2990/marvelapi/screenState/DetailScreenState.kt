package com.douglas2990.marvelapi.screenState

import androidx.annotation.StringRes
import com.douglas2990.marvelapi.model.modelDaniel.character.CharacterModel

sealed class DetailScreenState{
    //data class Success(val data: List<CharacterModelResponse>) : DetailScreenState()
    //data class Success(val data: String) : DetailScreenState()
    data class Success(val data: List<CharacterModel>) : DetailScreenState()
    data class Error(@StringRes val messageId: Int) : DetailScreenState()
    object Loading: DetailScreenState()
}
