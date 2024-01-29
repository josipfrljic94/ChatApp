package com.example.fragment

import com.example.fragment.dao.Product
import com.example.fragment.dao.ResponseProductItem

class ProductMapper {
    fun buildProduct(rpI: ResponseProductItem): Product {
        return Product(
            description = rpI.description,
            id=rpI.id,
            image = rpI.image,
            price=rpI.price,
            rating=rpI.rating,
            title=rpI.title
        )
    }
}