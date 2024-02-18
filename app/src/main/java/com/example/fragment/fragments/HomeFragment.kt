package com.example.fragment.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.fragment.ProductMapper
import com.example.fragment.R
import com.example.fragment.adapters.MealsAdapter
import com.example.fragment.dao.FoodRecipe
import com.example.fragment.dao.ResponseProduct
import com.example.fragment.dao.SectionLabel
import com.example.fragment.databinding.FragmentHomeBinding
import com.example.fragment.epoxy.ProductEpoxyController
import com.example.fragment.util.NetworkResponse
import com.example.fragment.viewmodel.MainViewModel
import com.example.fragment.viewmodel.SearchMealViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var searchMealViewModel: SearchMealViewModel
    private lateinit var mealsAdapter: MealsAdapter
    private lateinit var mealRecyclerView:RecyclerView
    private lateinit var epoxyRV:EpoxyRecyclerView
    private lateinit var  mView: View
//    private lateinit var binding: FragmentHomeBinding

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private var epoxyController:ProductEpoxyController?=null

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
        if(binding != null){
            binding!!.lifecycleOwner = this
            binding!!.viewModel = mainViewModel
//        mealRecyclerView=mView.findViewById(R.id.recyclerView)
            mealRecyclerView=binding!!.recyclerView
            epoxyRV=binding!!.epoxyRecyclerView
            epoxyRV.layoutManager=LinearLayoutManager(context)
            epoxyController= ProductEpoxyController()
            epoxyRV!!.setController(epoxyController!!)
            setupRecyclerView()
//            requestApiData()
//            requestProductApiData()
            mainViewModel.getAllProducts()
            updateProducts()
            setupInputListener()
        }

        return binding!!.root
    }



    fun setupInputListener() {
        binding!!.editText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {

                Toast.makeText(context,s.toString(),Toast.LENGTH_SHORT)
                mainViewModel.searchProductName(s.toString())
                Log.d("EditText",s.toString())
                updateProducts()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not used in this example
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Toast.makeText(context,s.toString(),Toast.LENGTH_SHORT)
                Log.d("EditText2",s.toString())
                mainViewModel.searchProductName(s.toString())

            }
        })
    }

    private fun requestApiData() {
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

    private fun updateProducts(){
        lifecycleScope.launch {
            mainViewModel._productDataState.collect{
                state->
                when (state){
                    is NetworkResponse.Loading -> Toast.makeText(context,"Products Loading",Toast.LENGTH_SHORT).show()

                    is NetworkResponse<ResponseProduct> ->{
                        val data=state.data
                        if(data!!.size>0){
                            val d=data
                                .filter { it.title.contains(mainViewModel.searchString) }
                                .map{
                                ProductMapper().buildProduct(it)
                            }
                            Log.d("Product_Title",d[0].title)
                            val sectionTitle=SectionLabel("Epoxy test",R.drawable.ic_people)

                            epoxyController?.updateData(sectionTitle,d)
                    }

                    }
                    else -> Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun requestProductApiData() {
        lifecycleScope.launch {
            mainViewModel.getProductResponse().collect { state ->

                        if(state.size>0){
                            val d=state.map{
                                    ProductMapper().buildProduct(it)
                                }
                            Log.d("Product_Title",d[0].title)
                            val sectionTitle=SectionLabel("Epoxy test",R.drawable.ic_people)

                            epoxyController?.updateData(sectionTitle,d)
                        }

//                        val epoxyController:ProductEpoxyController()
//                        epoxyController.setData(ResponseProduct!!)


                    }
//                    else -> Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show()

//                }
//            }
        }

    }

    private fun setupRecyclerView() {
        mealRecyclerView.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter=mealsAdapter
        }
    }



}