package com.example.fragment.service

import com.example.fragment.dao.FeedBaseballScoreBoard
import io.reactivex.Single
//import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface SportFeedService {
    @GET("sports/baseball/mlb/scoreboard")
    fun getMLBGames(): Single<FeedBaseballScoreBoard>

//    @GET("sports/soccer/usa.1/scoreboard")
//    fun getMLSGames(): Single<SoccerScoreboardData>
//
//    @GET("sports/hockey/nhl/scoreboard")
//    fun getNHLGames(): Single<DefaultScoreboardData>
//
//    @GET("sports/basketball/nba/scoreboard")
//    fun getNBAGames(): Single<DefaultScoreboardData>
}