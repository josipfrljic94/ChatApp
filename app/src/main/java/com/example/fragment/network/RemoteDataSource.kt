package com.example.fragment.network

import com.example.fragment.dao.FoodRecipe
import com.example.fragment.dao.ResponseProduct
import com.example.fragment.service.MealService
import com.example.fragment.service.ProductService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named


class RemoteDataSource @Inject constructor(@Named("food_retrofit") private val mealService: MealService,@Named("products_retrofit") private val productsService: ProductService){
    suspend fun getMeals(queries:Map<String,String>): Response<FoodRecipe> {
        return mealService.fetchMeals(queries)
    }

    suspend fun getProducts(): Response<ResponseProduct> {
        return productsService.fetchProducts()
    }
}