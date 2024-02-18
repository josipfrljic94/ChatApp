package com.example.fragment.epoxy

import androidx.core.content.ContextCompat
import com.example.fragment.R
import com.example.fragment.dao.SectionLabel
import com.example.fragment.databinding.EpoxySectionLabelBinding




data class SectionLabelEpoxyModel(val sectionLabel: SectionLabel)
    :ViewBindingKotlinModel<EpoxySectionLabelBinding>(R.layout.epoxy_section_label){


    override fun EpoxySectionLabelBinding.bind() {
       sectionTitle.text=sectionLabel.title
        sectionImg.setImageDrawable(ContextCompat.getDrawable(sectionImg.context,sectionLabel.color))
    }
}