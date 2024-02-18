package com.example.fragment.service

import com.example.fragment.dao.ResponseProduct
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {
    @GET("products")
    suspend fun fetchProducts(): Response<ResponseProduct>
}