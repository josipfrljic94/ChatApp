package com.example.fragment.network

import com.example.fragment.dao.FeedBaseballScoreBoard
import com.example.fragment.dao.FoodRecipe
import com.example.fragment.dao.ResponseProduct
import com.example.fragment.service.MealService
import com.example.fragment.service.ProductService
import com.example.fragment.service.SportFeedService
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named


class RemoteDataSource @Inject constructor(@Named("food_retrofit") private val mealService: MealService,@Named("products_retrofit") private val productsService: ProductService,@Named("sport_feed_retrofit") private val sportFeedService: SportFeedService){
    suspend fun getMeals(queries:Map<String,String>): Response<FoodRecipe> {
        return mealService.fetchMeals(queries)
    }

    suspend fun getProducts(): Response<ResponseProduct> {
        return productsService.fetchProducts()
    }

    fun getSportFeed():Single<FeedBaseballScoreBoard>{
        return sportFeedService.getMLBGames()
    }

    fun getMLSGames(): Single<FeedBaseballScoreBoard> {
        return sportFeedService.getMLSGames()
    }
}