package com.demo.presentation.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.demo.R
import com.demo.data.db.entities.MarketModel
import com.demo.data.utils.ResultState
import com.demo.data.utils.ResultState.*
import com.demo.databinding.ActivityMainBinding
import com.demo.presentation.ui.viewmodels.MarketListViewModel
import com.demo.presentation.ui.viewmodels.MarketViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}