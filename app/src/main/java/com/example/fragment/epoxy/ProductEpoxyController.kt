package com.example.fragment.epoxy

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.TypedEpoxyController
import com.example.fragment.dao.Product
import com.example.fragment.dao.ResponseProductItem

 class ProductEpoxyController : TypedEpoxyController<List<Product>>() {

    override fun buildModels(data:List<Product>) {
        if(data.isNullOrEmpty()) return
        data.forEach { productItem ->
            ProductEpoxyModel(productItem).id(productItem.id).addTo(this)
        }
    }


}
