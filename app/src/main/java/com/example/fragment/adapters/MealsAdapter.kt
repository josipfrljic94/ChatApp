package com.example.fragment.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavDirections
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fragment.R
import com.example.fragment.dao.Result
import com.example.fragment.databinding.MealAdapterBinding
import com.example.fragment.fragments.HomeFragmentDirections


class MealsAdapter(val context: Context?):RecyclerView.Adapter<MealsAdapter.MealViewHolder>() {
    private var mealList= ArrayList<Result>()

    fun setMeals(mealsList: ArrayList<Result>){
        mealList=mealsList
        notifyDataSetChanged()

    }


    class MealViewHolder(private val binding:MealAdapterBinding):RecyclerView.ViewHolder(binding.root){

        fun bindMeal(meal:Result){
                binding.meal=meal
                binding.executePendingBindings()
            }

        companion object {
            fun from(parent: ViewGroup): MealViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MealAdapterBinding.inflate(layoutInflater, parent, false)
                return MealViewHolder(binding)
            }
        }

    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Result>() {

            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
                oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder=MealViewHolder(MealAdapterBinding.inflate(
        LayoutInflater.from(parent.context),parent,false))


    override fun getItemCount(): Int {
        return mealList.size
    }


    override fun onBindViewHolder(holder: MealViewHolder, position: Int){
        holder.bindMeal(mealList[position])
        val bundle=Bundle()
        bundle.putString("title",mealList[position].title)

        holder.itemView.setOnClickListener{
            //option 1
            //findNavController(holder.itemView).navigate(R.id.action_homeFragment_to_mealDetailFragment,bundle)
            val action=HomeFragmentDirections.actionHomeFragmentToMealDetailFragment(mealList[position].title)
            findNavController(holder.itemView).navigate(action)
        }
    }
}