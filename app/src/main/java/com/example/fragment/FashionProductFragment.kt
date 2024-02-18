package com.example.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fragment.databinding.FragmentFashionProductBinding


class FashionProductFragment : Fragment() {


    companion object {
        fun newInstance() = FashionProductFragment()
    }

    private lateinit var viewModel: FashionProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ViewDataBinding? =
            DataBindingUtil.inflate(inflater, R.layout.fragment_fashion_product, container, false)
        binding
        return binding!!.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FashionProductViewModel::class.java)
        // TODO: Use the ViewModel
    }

}