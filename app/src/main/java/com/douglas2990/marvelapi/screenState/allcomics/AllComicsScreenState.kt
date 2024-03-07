package com.douglas2990.marvelapi.screenState.allcomics

import androidx.annotation.StringRes
import com.douglas2990.marvelapi.model.allComics.Result

sealed class AllComicsScreenState {

    data class Success(val data: List<Result>) : AllComicsScreenState()
    data class Error(@StringRes val messageId: Int): AllComicsScreenState()
    object Loading: AllComicsScreenState()

}