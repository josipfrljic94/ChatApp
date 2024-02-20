package com.example.fragment.dao

data class Competitor(
    val errors: Int,
    val hits: Int,
    val homeAway: String,
    val id: String,
    val order: Int,
    val score: String,
    val statistics: List<Any>,
    val team: Team,
    val type: String,
    val uid: String
)