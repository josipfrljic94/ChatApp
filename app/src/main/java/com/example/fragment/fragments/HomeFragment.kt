package com.example.fragment.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.fragment.R
import com.example.fragment.util.NetworkResponse
import com.example.fragment.viewmodel.MainViewModel
import com.example.fragment.viewmodel.SearchMealViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var searchMealViewModel: SearchMealViewModel
    private lateinit var  mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel= ViewModelProvider(requireActivity())[MainViewModel::class.java]
        searchMealViewModel= ViewModelProvider(requireActivity())[SearchMealViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView=inflater.inflate(R.layout.fragment_home, container, false)
        requestApiData()
        return mView
    }

    private fun requestApiData() {
        mainViewModel.getRecipes(searchMealViewModel.applyQueries())
        mainViewModel.mealResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResponse.Success -> {
//                    hideShimmerEffect()
//                    response.data?.let { mAdapter.setData(it) }
                    Log.d("HOME FRAGMENT",response.data.toString())
                }

                is NetworkResponse.Error -> {
//                    hideShimmerEffect()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is NetworkResponse.Loading -> {
//                    showShimmerEffect()
                }


            }
        }
    }


}