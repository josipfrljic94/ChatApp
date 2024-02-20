package com.example.fragment.dao

data class LinkXX(
    val href: String,
    val isExternal: Boolean,
    val isPremium: Boolean,
    val language: String,
    val rel: List<String>,
    val shortText: String,
    val text: String
)