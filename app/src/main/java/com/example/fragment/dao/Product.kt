package com.example.fragment.dao

data class Product(
                     val description: String,
                     val id: Int,
                     val image: String,
                     val price: Double,
                     val rating:Rating,
                     val title: String)