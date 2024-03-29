package com.example.fragment.epoxy

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.TypedEpoxyController
import com.example.fragment.R
import com.example.fragment.dao.League
import com.example.fragment.dao.Product
import com.example.fragment.dao.ResponseProductItem
import com.example.fragment.dao.SectionLabel

class ProductEpoxyController: EpoxyController() {


     private var products: List<Product> = emptyList()
     private var section:SectionLabel?=null
    private var leagues: List<League> = emptyList()
    private val filterIds: MutableList<Int> = mutableListOf()

    override fun buildModels() {

         if(section != null){
             addSectionTitleModel(section!!)
         }

         if(!leagues.isNullOrEmpty()){
             leagues.forEach {
                 when(it){
                     is League ->{
                         addLeague(it)
                     }
                 }
             }
         }

         if(!products.isNullOrEmpty()){
             products
//                 .filter {
//                     filterIds.any{id ->
//                             id != it.id
//                         }
//                 }
                 .filter {
                     !filterIds.contains(it.id)
                 }
                 .forEach{ item->
                 when(item){
                     is  Product->{
                         addProduct(item)
                     }
                 }
             }

         }
     }

    private fun deleteProduct(id:Int){
        filterIds.add(id)
        requestModelBuild()
    }
     private fun addProduct(p: Product) {
         ProductEpoxyModel(p){deleteProduct(p.id)}
             .id(p.id)
             .addTo(this)
     }

    private fun addLeague(p: League) {
        LeagueEpoxyModel(p)
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

    fun setLeagueData(leagues: List<League>) {
        this.leagues = leagues
        requestModelBuild()
    }


 }
