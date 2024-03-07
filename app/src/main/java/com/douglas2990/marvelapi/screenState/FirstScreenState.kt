package com.douglas2990.marvelapi.screenState

import androidx.annotation.StringRes
import com.douglas2990.marvelapi.model.Comics
import com.douglas2990.marvelapi.model.Item
import com.douglas2990.marvelapi.model.Result

sealed class FirstScreenState {
    //data class Success(val data: List<Item>) : FirstScreenState()
    data class Success(val data: List<Result>) : FirstScreenState()
    data class Error(@StringRes val messageId: Int): FirstScreenState()
    object Loading: FirstScreenState()
}