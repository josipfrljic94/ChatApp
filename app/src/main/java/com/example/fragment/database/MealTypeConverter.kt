package com.example.fragment.database

import androidx.room.TypeConverter
import com.example.fragment.dao.FoodRecipe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MealTypeConverter {

    private val gs= Gson()
    @TypeConverter
    fun mealToString(meal:FoodRecipe):String{
        return gs.toJson(meal)
    }

    @TypeConverter
    fun jsonToMeal(json:String):FoodRecipe{
        val type=object : TypeToken<FoodRecipe>() {}.type
        return gs.fromJson(json,type)
    }
}
