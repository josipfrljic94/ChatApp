package com.example.fragment.repository

import com.example.fragment.database.MealDao
import com.example.fragment.database.MealEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val mealDao: MealDao
) {
    fun readDatabase(): Flow<List<MealEntity>> {
        return mealDao.readMeals()
    }

    suspend fun insertRecipes(mealEntity: MealEntity) {
        mealDao.insertMeal(mealEntity)
    }
}