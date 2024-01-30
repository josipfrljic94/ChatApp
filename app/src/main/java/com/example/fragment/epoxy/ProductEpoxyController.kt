package com.example.fragment.epoxy

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.TypedEpoxyController
import com.example.fragment.R
import com.example.fragment.dao.Product
import com.example.fragment.dao.ResponseProductItem
import com.example.fragment.dao.SectionLabel

class ProductEpoxyController: EpoxyController() {


     private var products: List<Product> = emptyList()
     private var section:SectionLabel?=null
     override fun buildModels() {

         if(section != null){
             addSectionTitleModel(section!!)
         }

         if(!products.isNullOrEmpty()){
             products.forEach{ item->
                 when(item){
                     is  Product->{
                         addProduct(item)
                     }
                 }
             }
         }
     }

     private fun addProduct(p: Product) {
         ProductEpoxyModel(p)
             .id(p.id)
             .addTo(this)
     }

    private fun addSectionTitleModel(sectionLabel: SectionLabel) {
        SectionLabelEpoxyModel(sectionLabel)
            .id(sectionLabel.title)
            .addTo(this)
    }

     fun updateData(sectionLabel: SectionLabel, products: List<Product>) {
         this.section = sectionLabel
         this.products = products
         requestModelBuild()
     }


 }
