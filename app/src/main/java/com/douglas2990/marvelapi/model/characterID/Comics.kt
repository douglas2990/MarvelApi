package com.douglas2990.marvelapi.model.characterID

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)