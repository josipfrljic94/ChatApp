package com.example.fragment.network

import com.example.fragment.dao.FoodRecipe
import com.example.fragment.service.MealService
import retrofit2.Response
import javax.inject.Inject


class RemoteDataSource @Inject constructor(private val mealService: MealService){
    suspend fun getMeals(queries:Map<String,String>): Response<FoodRecipe> {
        return mealService.fetchMeals(queries)
    }
}