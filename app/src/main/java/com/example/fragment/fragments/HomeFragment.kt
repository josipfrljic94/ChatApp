package com.example.fragment.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragment.R
import com.example.fragment.adapters.MealsAdapter
import com.example.fragment.dao.FoodRecipe
import com.example.fragment.databinding.FragmentHomeBinding
import com.example.fragment.util.NetworkResponse
import com.example.fragment.viewmodel.MainViewModel
import com.example.fragment.viewmodel.SearchMealViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var searchMealViewModel: SearchMealViewModel
    private lateinit var mealsAdapter: MealsAdapter
    private lateinit var mealRecyclerView:RecyclerView
    private lateinit var  mView: View
//    private lateinit var binding: FragmentHomeBinding

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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
//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        _binding=FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = mainViewModel
//        mealRecyclerView=mView.findViewById(R.id.recyclerView)
        mealRecyclerView=binding.recyclerView
        setupRecyclerView()
        requestApiData()
        return binding.root
    }

    private fun requestApiData() {
//        mainViewModel.getRecipes(searchMealViewModel.applyQueries())
        lifecycleScope.launch {
            mainViewModel.getRecipesResponse(searchMealViewModel.applyQueries()).collect { state ->
                when (state) {
//                    is NetworkResponse.Loading -> Toast.makeText(context,"Loading",Toast.LENGTH_SHORT).show()
                    is FoodRecipe -> {
                        Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()
//                        Log.d("HFSUCCESS",state.state.toString())
                        mealsAdapter.setMeals(ArrayList(state?.results))


                    }
                    else -> Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show()

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


//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding.viewModel = mainViewModel
//        binding.lifecycleOwner = viewLifecycleOwner
//    }


}