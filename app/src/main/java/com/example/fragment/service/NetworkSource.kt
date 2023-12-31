package com.example.fragment.service

import com.example.fragment.dao.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class NetworkSource @Inject constructor(
    private val mealService: MealService
) {

    suspend fun getMealsData(querry:Map<String,String>): Response<FoodRecipe> {
        return mealService.fetchMeals(querry)
    }
}