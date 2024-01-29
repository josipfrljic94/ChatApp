package com.example.fragment.di

import com.example.fragment.service.MealService
import com.example.fragment.service.ProductService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.annotation.Nonnull
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {


    @Provides
    @Singleton
    @Named("food_retrofit")
    fun provideRetrofitInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("food_retrofit")
    fun provideMealService(@Nonnull @Named("food_retrofit") retrofit: Retrofit):MealService{
        return retrofit.create(MealService::class.java)
    }

    private var gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    @Singleton
    @Named("products_retrofit")
    fun provideRetrofitProductInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
//            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }


        @Provides
        @Singleton
        @Named("products_retrofit")
        fun provideProductService(@Nonnull @Named("products_retrofit") retrofit: Retrofit): ProductService {
            return retrofit.create(ProductService::class.java)
        }


}