package com.example.fragment.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.fragment.R
import com.example.fragment.databinding.FragmentMealDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MealDetailFragment : Fragment() {
    private  var title: String?=null
    private  var imgUrl:String?=null
    private lateinit var binding:FragmentMealDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString("title","Default")
            imgUrl=it.getString("imgUrl","")
        }


//        if (actionBar != null) {
//            actionBar.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FF5733")))
//        }
    }

    private fun setInfoInView(title:String, imgUrl:String) {
        if(imgUrl.isNotEmpty() || imgUrl != ""){
            Glide.with(this)
                .load(imgUrl)
                .into(binding.imgMealDetail)

            binding.collapsingToolbar.title=title
            binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
            binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))

        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_meal_detail, container, false)
        binding=FragmentMealDetailBinding.inflate(layoutInflater)
        Log.d("MEAILDETAILFRAGMENT","${title} - ${imgUrl}")
        setInfoInView(title ?:"",imgUrl ?: "")
        val ac=requireActivity() as? androidx.appcompat.app.AppCompatActivity
        val actionBar = ac?.supportActionBar
        // Change ActionBar (Toolbar) color dynamically
        actionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.green)))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ac?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.green);
        }
        return binding.root
    }


}