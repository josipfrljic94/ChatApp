package com.example.fragment.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fragment.dao.FoodRecipe
import com.example.fragment.util.Constants

@Entity(tableName = Constants.MEAL_TABLE)
class MealEntity(
    var mealRecepie: FoodRecipe
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}