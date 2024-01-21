package com.example.fragment.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fragment.dao.FoodRecipe
import com.example.fragment.database.MealEntity
import com.example.fragment.repository.Repository
import com.example.fragment.util.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository):ViewModel(),
    LifecycleObserver {
//    val _dataState:Flowable<NetworkResponse<FoodRecipe>> = (NetworkResponse.Loading())
    val _dataState: MutableStateFlow<NetworkResponse<FoodRecipe>> = MutableStateFlow(NetworkResponse.Loading())


    /** ROOM DATABASE */

    val readRecipes: LiveData<List<MealEntity>> = repository.local.readDatabase().asLiveData()

    private fun insertRecipes(mealEntity: MealEntity) =
        viewModelScope.launch(Dispatchers.IO){
            repository.local.insertRecipes(mealEntity = mealEntity)
        }

    private fun offlineCacheRecipes(foodRecipe: FoodRecipe) {
        val recipesEntity = MealEntity(foodRecipe)
        insertRecipes(recipesEntity)
    }





//    fun getRecipes(queries:Map<String,String>)=viewModelScope.launch {
//        getRecipesResponse(queries)
//    }

     suspend fun getRecipesResponse(queries: Map<String, String>)=flow<FoodRecipe> {
        _dataState.value=NetworkResponse.Loading()
        try {
            val response=repository.remote.getMeals(queries)
            emit(response.body()!!)
            val foodRecipe = response.body()
            if(foodRecipe != null) {
                offlineCacheRecipes(foodRecipe)
            }
//            _dataState.value=handleFoodRecipesResponse(response)
        }catch (e:Exception){
//                emit(e)
//            _dataState.value=NetworkResponse.Error("Recepies not found")
        }
         viewModelScope.launch(Dispatchers.IO){

         }
    }

    private fun handleFoodRecipesResponse(response: Response<FoodRecipe>): NetworkResponse<FoodRecipe> {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResponse.Error("Timeout")
            }
            response.code() == 402 -> {
                return NetworkResponse.Error("API Key Limited.")
            }
            response.body()!!.results.isNullOrEmpty() -> {
                return NetworkResponse.Error("Recipes not found.")
            }
            response.isSuccessful -> {
                val foodRecipes = response.body()
                return NetworkResponse.Success(foodRecipes!!)
            }
            else -> {
                return NetworkResponse.Error(response.message())
            }
        }
    }



}