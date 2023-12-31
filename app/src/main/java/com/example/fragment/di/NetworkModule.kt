package com.example.fragment.di

import com.example.fragment.service.MealService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {


    @Provides
    @Singleton
    fun provideRetrofitInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMealService(retrofit: Retrofit):MealService{
        return retrofit.create(MealService::class.java)
    }

    //strong  i dynamic



}