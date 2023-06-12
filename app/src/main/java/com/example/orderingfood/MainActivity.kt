package com.example.orderingfood

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.orderingfood.databinding.ActivityMainBinding
import com.example.orderingfood.di.AppComponent

class MainActivity : AppCompatActivity() {

    private lateinit var appComponent: AppComponent
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appComponent = (application as App).appComponent
        appComponent.inject(this)
        Log.d("AppComponent", "Injecting MainActivity")
        setupNavigation()
    }

    private fun setupNavigation() {
        val bottomNavigation = binding.navView
        //получаем доступ к фрагменту, в который будем вставлять другие фрагменты, через supportFragmentManager
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // ограничиваем видимость BottomNavigationView, чтобы было активно только на главные фрагменты
        val navController = navHostFragment.navController
        bottomNavigation.setupWithNavController(navController)
    }
}