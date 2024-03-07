package com.douglas2990.marvelapi.model.allComics

data class Creators(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)