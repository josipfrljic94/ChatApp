package com.example.fragment.dao

data class GeoBroadcast(
    val lang: String,
    val market: Market,
    val media: Media,
    val region: String,
    val type: Type
)