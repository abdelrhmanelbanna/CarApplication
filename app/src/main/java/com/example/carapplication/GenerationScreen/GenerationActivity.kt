package com.example.carapplication.GenerationScreen

import android.content.Intent
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
import androidx.activity.viewModels
import com.example.carapplication.BrandScreen.BrandActivity
import com.example.carapplication.ModelScreen.ModelActivity


@AndroidEntryPoint
class GenerationActivity : AppCompatActivity() {

    val viewModel: GenerationViewModel by viewModels()

    lateinit var binding: ActivityGenerationBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        viewModel =ViewModelProvider(this).get(GenerationViewModel::class)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_generation)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val attribute_id = intent.getIntExtra("attribute_id", -1)
        val vehicle_id = intent.getIntExtra("vehicle_id", -1)
        val attribute_value_id = intent.getIntExtra("attribute_value_id", -1)

        val brandImage= intent.getStringExtra("brand_image")
        binding.imageBind = brandImage

        viewModel.getVehicle(attribute_id, vehicle_id, attribute_value_id)
        val adapter = VehicleAdapter()

        viewModel.vehicleLiveData.observe(this) { vehicleList ->
            val firstVehicle = vehicleList?.first()
            val vehiclesAdapter = vehicleList?.drop(1)

//            val differenceList = vehicleList?.get(0)?.extraAttributes

            adapter.changeData(vehiclesAdapter)
            binding.binding = firstVehicle

        }

        binding.recycleView.adapter = adapter


        var isFavorite = false
        binding.icFavourite.setOnClickListener {

            isFavorite = !isFavorite

            val tintColor = if (isFavorite) {
                getColor(R.color.primary)
            } else {
                getColor(R.color.gray)
            }
            binding.icFavourite.setColorFilter(tintColor)
        }

        var isCompare = false
        binding.icCompare.setOnClickListener {
            isCompare = !isCompare

            val tintColor = if (isCompare) {
                getColor(R.color.primary)
            } else {
                getColor(R.color.gray)
            }

            binding.icCompare.setColorFilter(tintColor)
        }


//        adapter.onDifferenceClickListener = object : VehicleAdapter.OnDifferenceClickListener {
//            override fun onDifferenceClick(pos: Int, item: Vehicle?) {
//
//            }
//        }

        navigateToHome()

    }

    private fun navigateToHome() {
        binding.icBack.setOnClickListener{
            val intent = Intent(this, ModelActivity::class.java)
            startActivity(intent)
        }
    }


}