package com.douglas2990.marvelapi.model.allComics

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Any>,
    val returned: Int
)