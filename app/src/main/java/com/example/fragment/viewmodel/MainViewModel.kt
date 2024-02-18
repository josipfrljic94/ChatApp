package com.example.fragment.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fragment.ProductMapper
import com.example.fragment.dao.FoodRecipe
import com.example.fragment.dao.Product
import com.example.fragment.dao.ResponseProduct
import com.example.fragment.database.MealEntity
import com.example.fragment.repository.Repository
import com.example.fragment.util.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel(),
    LifecycleObserver {
    val _productDataState: MutableStateFlow<NetworkResponse<ResponseProduct>> =
        MutableStateFlow(NetworkResponse.Loading())

    //    val _dataState:Flowable<NetworkResponse<FoodRecipe>> = (NetworkResponse.Loading())
//    val _dataProduct: LiveData<NetworkResponse<ResponseProduct>> = (NetworkResponse.Loading).asLiveData()
//    val readRecipes: LiveData<List<RecipesEntity>> = repository.local.readDatabase().asLiveData()
    val _dataState: MutableStateFlow<NetworkResponse<FoodRecipe>> =
        MutableStateFlow(NetworkResponse.Loading())


//    val searchString: MutableStateFlow<String> = MutableStateFlow("")
var searchString=""
    /** ROOM DATABASE */

    val readRecipes: LiveData<List<MealEntity>> = repository.local.readDatabase().asLiveData()

    private fun insertRecipes(mealEntity: MealEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertRecipes(mealEntity = mealEntity)
        }

    private fun offlineCacheRecipes(foodRecipe: FoodRecipe) {
        val recipesEntity = MealEntity(foodRecipe)
        insertRecipes(recipesEntity)
    }


    fun searchProductName(productName: String = "") {
//        searchString.value = productName
        searchString=productName
    }


    suspend fun getRecipesResponse(queries: Map<String, String>) = flow<FoodRecipe> {
        _dataState.value = NetworkResponse.Loading()
        try {
            val response = repository.remote.getMeals(queries)
            emit(response.body()!!)
            val foodRecipe = response.body()
            if (foodRecipe != null) {
                offlineCacheRecipes(foodRecipe)
            }
//            _dataState.value=handleFoodRecipesResponse(response)
        } catch (e: Exception) {
//                emit(e)
//            _dataState.value=NetworkResponse.Error("Recepies not found")
        }
        viewModelScope.launch(Dispatchers.IO) {

        }
    }


    private suspend fun getProducts() {
        _productDataState.value = NetworkResponse.Loading()
        try {
            val response = repository.remote.getProducts()
            _productDataState.value = handleProductResponse(response)
        } catch (e: Exception) {
            _productDataState.value =
                NetworkResponse.Error("Recipies not found, error: ${e.message}")
        }
    }

    fun getAllProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            getProducts()
        }
    }

    suspend fun getProductResponse() = flow<ResponseProduct> {
        _dataState.value = NetworkResponse.Loading()
        try {
            val response = repository.remote.getProducts()
            emit(response.body()!!)
//            val foodRecipe = response.body()
//            if(foodRecipe != null) {
//                offlineCacheRecipes(foodRecipe)
//            }
//            _dataState.value=handleFoodRecipesResponse(response)
//             _productDataState.value=handleProductResponse(response)
        } catch (e: Exception) {
//                emit(e)
//            _dataState.value=NetworkResponse.Error("Recepies not found")
            Log.d("Erorror insde", e.message.toString())
        }
//        viewModelScope.launch(Dispatchers.IO){
//
//        }
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

    private fun handleProductResponse(response: Response<ResponseProduct>): NetworkResponse<ResponseProduct> {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResponse.Error("Timeout")
            }

            response.code() == 402 -> {
                return NetworkResponse.Error("API Key Limited.")
            }

            response.body()!!.isNullOrEmpty() -> {
                return NetworkResponse.Error("Recipes not found.")
            }

            response.isSuccessful -> {
                val productsResponse = response.body()
                return NetworkResponse.Success(productsResponse!!)
            }

            else -> {
                return NetworkResponse.Error(response.message())
            }
        }
    }


}