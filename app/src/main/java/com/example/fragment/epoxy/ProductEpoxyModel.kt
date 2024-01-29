package com.example.fragment.epoxy

import coil.load
import com.example.fragment.R
import com.example.fragment.dao.Product
import com.example.fragment.databinding.ProductItemBinding


data class ProductEpoxyModel(val product: Product)
    :ViewBindingKotlinModel<ProductItemBinding>(R.layout.product_item){


    override fun ProductItemBinding.bind() {
        productName.text=product.title
        productDesc.text=product.description
        rating.text=product.rating.toString()
        price.text=product.price.toString()
        productItemImg.load(product.image){
            crossfade(600)
            error(R.drawable.ic_launcher_background)
        }
    }
}