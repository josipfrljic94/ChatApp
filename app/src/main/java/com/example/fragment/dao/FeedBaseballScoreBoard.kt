package com.example.fragment.dao

data class FeedBaseballScoreBoard(
    val day: Day,
    val events: List<Event>,
    val leagues: List<League>,
    val season: SeasonXX
)