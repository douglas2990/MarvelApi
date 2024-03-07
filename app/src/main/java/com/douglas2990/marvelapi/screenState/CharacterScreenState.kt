package com.douglas2990.marvelapi.screenState

import androidx.annotation.StringRes
import com.douglas2990.marvelapi.model.modelDaniel.character.CharacterModel

sealed class CharacterScreenState {

    data class Success(val data: List<CharacterModel>) : CharacterScreenState()
    data class Error(@StringRes val messageId: Int) : CharacterScreenState()
    object Loading: CharacterScreenState()
}