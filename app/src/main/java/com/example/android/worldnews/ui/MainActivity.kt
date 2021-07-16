package com.example.android.worldnews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.android.worldnews.R
import com.example.android.worldnews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val binding=ActivityMainBinding.inflate(layoutInflater)


        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val navController = findNavController(R.id.nav_host_fragment_container)
        binding.bottomNavigationView.setupWithNavController(navController)


    }
}