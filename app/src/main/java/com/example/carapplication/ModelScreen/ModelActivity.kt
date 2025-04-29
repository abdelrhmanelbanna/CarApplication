package com.example.carapplication.ModelScreen

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.carapplication.R
import com.example.carapplication.databinding.ActivityModelBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ModelActivity : AppCompatActivity() {

    lateinit var viewModel: ModelsViewModel
    lateinit var viewBinding : ActivityModelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ModelsViewModel::class)

        //  viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_brand)
        viewBinding = DataBindingUtil.setContentView(this,R.layout.activity_model)

        val brandId = intent.getIntExtra("brand_id", -1)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val adapter = ModelAdapter()

        viewModel.modelsLivedata.observe(this,{ modelsResponse->
            adapter.changeData(modelsResponse)
        })

        viewBinding.recyclerView.adapter = adapter

        viewModel.getModels(brandId)
    }
}