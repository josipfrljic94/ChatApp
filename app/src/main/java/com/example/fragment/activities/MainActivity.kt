package com.example.fragment.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.fragment.R
import com.example.fragment.databinding.ActivityMainBinding
import com.example.fragment.fragments.HomeFragment
import com.example.fragment.fragments.ListOnlineFragment
import com.example.fragment.service.MealService
import com.example.fragment.service.NetworkSource
import com.google.android.material.navigation.NavigationView

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var hf:HomeFragment
    private lateinit var loF:ListOnlineFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(view)
        binding.include.topAppBar.setNavigationOnClickListener(){
            binding.drawerLayout.open()
        }

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item selected
            menuItem.isChecked = true
            binding.drawerLayout.close()
            true
        }
        hf= HomeFragment()
        loF= ListOnlineFragment()
        fragmentSupplayer(hf)
        setupDrawerContent(binding.navView)

//        NetworkSource().getMealsData(mapOf("keyOf" to "dc953b44c20c4685b0f7d980b7e90d8b"))
    }

    private fun setupDrawerContent(navigationView: NavigationView){
        navigationView.setNavigationItemSelectedListener{menuItem->
            selectDrawerItem(menuItem)
            true
        }
    }

    private fun selectDrawerItem(menuItem: MenuItem):Unit {
        when(menuItem.itemId){
            R.id.homeFragment->fragmentSupplayer(hf)
            R.id.listOnlineFragment->fragmentSupplayer(loF)
        }
    }


    private  fun fragmentSupplayer(fragment:Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(binding.flContent.id,fragment)
            commit()
            if(!binding.drawerLayout.isActivated){
                binding.drawerLayout.close()
            }
        }
    }
}