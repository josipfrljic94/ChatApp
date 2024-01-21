package com.example.fragment.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface MealDao {
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun insertMeal(mealEntity: MealEntity)

    @Query("SELECT * FROM meal_table ORDER BY id ASC")
    fun readMeals(): Flow<List<MealEntity>>

//    @Query("SELECT  * FROM favorite_meal_table ORDER_BY id ASC")
//    fun readFavoriteMeals():Flow<List<FavoritesEntity>>
}