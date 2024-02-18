package com.example.fragment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.fragment.R
import com.example.fragment.binders.SportFeedBinder
import com.example.fragment.databinding.FragmentSportFeedBinding


class SportFeedFragment : Fragment() {

    private lateinit var binding: FragmentSportFeedBinding
    private lateinit var sportFeedBinder:SportFeedBinder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_sport_feed,container,false)
        binding.lifecycleOwner = viewLifecycleOwner

        sportFeedBinder= SportFeedBinder("Tottenham - Manchester United")
        val root=binding.root
        binding.sportFeed=sportFeedBinder
        return binding.root
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            SportFeedFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }


}