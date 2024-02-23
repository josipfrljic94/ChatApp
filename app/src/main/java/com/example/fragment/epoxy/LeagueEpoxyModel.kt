package com.example.fragment.epoxy

import coil.load
import com.example.fragment.R
import com.example.fragment.dao.League
import com.example.fragment.dao.Product
import com.example.fragment.databinding.LeagueItemBinding
import com.example.fragment.databinding.ProductItemBinding


data class LeagueEpoxyModel(val product: League)
    :ViewBindingKotlinModel<LeagueItemBinding>(R.layout.league_item){

    override fun LeagueItemBinding.bind() {
        leagueTitle.text=product.name
//        productDesc.text=product.description
//        rating.text=product.rating.toString()
//        price.text=product.price.toString()
//        productItemImg.load(product.image){
//            crossfade(600)
//            error(R.drawable.ic_launcher_background)
//        }
    }


}