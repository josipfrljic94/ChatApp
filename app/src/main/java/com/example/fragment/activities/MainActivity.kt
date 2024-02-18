package com.example.fragment.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fragment.R
import com.example.fragment.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val navController by lazy {
        Navigation.findNavController(this, R.id.nav_host_fragment)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbarInclude.topAppBar)
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.homeFragment, R.id.listOnlineFragment,R.id.sportFeedFragment), binding.drawerLayout)
        setupDrawerContent(binding.navView,appBarConfiguration)

    }

    private fun setupDrawerContent(navigationView: NavigationView,appBarConfiguration: AppBarConfiguration){
        navigationView.setupWithNavController(navController)
        //if you dont want toolbar is connected
        // setupActionBarWithNavController(navController, appBarConfiguration)


        //if you want back setup  this bind toolbar state with current state
        NavigationUI.setupActionBarWithNavController(this, navController,binding.drawerLayout)
    }


    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, binding.drawerLayout)
    }



    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}