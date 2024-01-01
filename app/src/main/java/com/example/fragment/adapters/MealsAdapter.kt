package com.example.fragment.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fragment.R
import com.example.fragment.dao.Result


class MealsAdapter(val context: Context?):RecyclerView.Adapter<MealsAdapter.MealViewHolder>() {
    private var mealList= ArrayList<Result>()
    lateinit var onItemClick:((Result)->Unit)


    fun setMeals(mealsList: ArrayList<Result>){
        mealList=mealsList
        notifyDataSetChanged()

    }


    class MealViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val mealNameTextView: TextView = itemView.findViewById(R.id.mealName)
        val mealImageView: ImageView = itemView.findViewById(R.id.mealImg)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.meal_adapter, parent, false)
        return MealViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val item=mealList[position]


        Glide.with(holder.itemView)
            .load(item.image)
            .into(holder.mealImageView)

        holder.mealNameTextView.text=item.title
    }
}