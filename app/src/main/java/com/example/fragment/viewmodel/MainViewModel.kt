package com.example.fragment.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fragment.dao.FoodRecipe
import com.example.fragment.repository.Repository
import com.example.fragment.util.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository):ViewModel(),
    LifecycleObserver {
//    val _dataState:Flowable<NetworkResponse<FoodRecipe>> = (NetworkResponse.Loading())
    val _dataState: MutableStateFlow<NetworkResponse<FoodRecipe>> = MutableStateFlow(NetworkResponse.Loading())


    fun getRecipes(queries:Map<String,String>)=viewModelScope.launch {
        getRecipesResponse(queries)
    }

    private suspend fun getRecipesResponse(queries: Map<String, String>) {
        _dataState.value=NetworkResponse.Loading()
        try {
            val response=repository.remote.getMeals(queries)
            _dataState.value=handleFoodRecipesResponse(response)
        }catch (e:Exception){
            _dataState.value=NetworkResponse.Error("Recepies not found")
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