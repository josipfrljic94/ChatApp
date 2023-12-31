package com.example.fragment.service

import com.example.fragment.dao.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MealService {

    @GET("/recipes/complexSearch")
    suspend fun fetchMeals(@QueryMap query:Map<String,String>):Response<FoodRecipe>
}