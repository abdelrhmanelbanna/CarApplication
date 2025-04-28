package com.example.carapplication.BrandScreen

import android.app.Activity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.example.carapplication.R
import com.example.carapplication.databinding.ActivityBrandBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BrandActivity : AppCompatActivity() {


    lateinit var viewModel: BrandsViewModel
    lateinit var  viewDataBinding: ActivityBrandBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(BrandsViewModel::class)

        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_brand)
        enableEdgeToEdge()
       // setContentView(R.layout.activity_brand)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val adapter = BrandAdapter()
        viewModel.getBrands()
        viewModel.brandsLiveData.observe(this,{ brandsResponse->
            adapter.changeData(brandsResponse)
        })

        viewDataBinding.recyclerView.adapter = adapter


    }

}