package com.example.fragment.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fragment.R
import com.example.fragment.adapters.MealsAdapter
import com.example.fragment.util.NetworkResponse
import com.example.fragment.viewmodel.MainViewModel
import com.example.fragment.viewmodel.SearchMealViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var searchMealViewModel: SearchMealViewModel
    private lateinit var mealsAdapter: MealsAdapter
    private lateinit var mealRecyclerView:RecyclerView
    private lateinit var  mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel= ViewModelProvider(requireActivity())[MainViewModel::class.java]
        searchMealViewModel= ViewModelProvider(requireActivity())[SearchMealViewModel::class.java]
        mealsAdapter= MealsAdapter(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView=inflater.inflate(R.layout.fragment_home, container, false)
        mealRecyclerView=mView.findViewById(R.id.recyclerView)
        setupRecyclerView()
        requestApiData()
        return mView
    }

    private fun requestApiData() {
        mainViewModel.getRecipes(searchMealViewModel.applyQueries())
        lifecycleScope.launchWhenStarted {
            mainViewModel._dataState.collect { state ->
                when (state) {
                    is NetworkResponse.Loading -> Toast.makeText(context,"Loading",Toast.LENGTH_LONG).show()
                    is NetworkResponse.Success -> {
                        Toast.makeText(context,"Success",Toast.LENGTH_LONG).show()
                        Log.d("HFSUCCESS",state.data.toString())
                        mealsAdapter.setMeals(ArrayList(state.data?.results))


                    }
                    is NetworkResponse.Error -> Toast.makeText(context,"Error",Toast.LENGTH_LONG).show()

                }
            }
        }

    }

    private fun setupRecyclerView() {
        mealRecyclerView.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter=mealsAdapter
        }
    }



}