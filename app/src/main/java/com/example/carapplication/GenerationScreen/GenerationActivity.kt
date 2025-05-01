package com.example.carapplication.GenerationScreen

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.carapplication.R
import com.example.carapplication.databinding.ActivityGenerationBinding
import com.example.domain.model.Vehicle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenerationActivity : AppCompatActivity() {

    lateinit var viewModel : GenerationViewModel

    lateinit var binding : ActivityGenerationBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel =ViewModelProvider(this).get(GenerationViewModel::class)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_generation)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel.getVehicle(195,180,2916)
        val adapter = VehicleAdapter()

        viewModel.vehicleLiveData.observe(this) { vehicleList ->
            val firstVehicle = vehicleList?.first()
            val vehiclesAdapter = vehicleList?.drop(1)
            adapter.changeData(vehiclesAdapter)
            binding.binding = firstVehicle
        }

        binding.recycleView.adapter = adapter





    }



}