package com.example.fragment.epoxy

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.TypedEpoxyController
import com.example.fragment.R
import com.example.fragment.dao.League
import com.example.fragment.dao.Product
import com.example.fragment.dao.ResponseProductItem
import com.example.fragment.dao.SectionLabel

class LeagueEpoxyController: EpoxyController() {


    private var leagues: List<League> = emptyList()

    override fun buildModels() {

        if(!leagues.isNullOrEmpty()){
            leagues.forEach{ item->
                when(item){
                    is  League->{
                        addLeague(item)
                    }
                }
            }
        }
    }

    private fun addLeague(p: League) {
        LeagueEpoxyModel(p)
            .id(p.id)
            .addTo(this)
    }

    fun updateData(leagues: List<League>) {
        this.leagues = leagues
        requestModelBuild()
    }


}
