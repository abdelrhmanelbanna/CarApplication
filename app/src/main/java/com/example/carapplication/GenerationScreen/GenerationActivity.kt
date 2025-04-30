package com.example.carapplication.GenerationScreen

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.carapplication.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenerationActivity : AppCompatActivity() {

    lateinit var viewModel : GenerationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel =ViewModelProvider(this).get(GenerationViewModel::class)

        enableEdgeToEdge()

        setContentView(R.layout.activity_generation)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel.getVehicle(195,180,2916)

    }



}