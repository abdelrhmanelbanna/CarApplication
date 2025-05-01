package com.example.carapplication.BrandScreen

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.carapplication.ModelScreen.ModelActivity
import com.example.carapplication.R
import com.example.carapplication.databinding.ActivityBrandBinding
import com.example.domain.model.Brand
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BrandActivity : AppCompatActivity() {
    private val viewModel: BrandsViewModel by viewModels()
    private lateinit var viewDataBinding: ActivityBrandBinding
    private val adapter = BrandAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_brand)
        enableEdgeToEdge()
        viewModel.getBrands()
        setupObservers()
        setUpAdapter()
        setUpSearchListener()

    }

    private fun setUpSearchListener() {
        viewDataBinding.searchBar.editText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed before text changes
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No action needed during text changes
            }

            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim()
                viewModel.filterBrands(query)
            }
        })
    }

    private fun setupObservers() {
        viewModel.brandsState.observe(this) { state ->
            Log.d("BrandActivity", "Received state: $state")
            when (state) {
                is BrandState.Loading -> {
                    viewDataBinding.progressBar.visibility = View.VISIBLE
                    viewDataBinding.recyclerView.visibility = View.GONE
                }
                is BrandState.Success -> {
                    viewDataBinding.progressBar.visibility = View.GONE
                    viewDataBinding.recyclerView.visibility = View.VISIBLE
                    Log.d("BrandActivity", "Updating adapter with ${state.brands.size} brands")
                    adapter.changeData(state.brands)
                }
                is BrandState.Error -> {
                    viewDataBinding.progressBar.visibility = View.GONE
                    viewDataBinding.recyclerView.visibility = View.VISIBLE
                    Log.e("BrandActivity", "Error: ${state.message}")
                    Snackbar.make(
                        viewDataBinding.recyclerView,
                        state.message,
                        Snackbar.LENGTH_LONG
                    ).setAction("Retry") {
                        viewModel.getBrands()
                    }.show()
                }
            }
        }
    }
    private fun setUpAdapter() {
        adapter.onItemClickListener = object : BrandAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, item: Brand?) {
                // nav to Model Screen
                val intent = Intent(this@BrandActivity, ModelActivity::class.java)
                intent.putExtra("brand_id", item?.id)
                intent.putExtra("brand_image",item?.image)
                startActivity(intent)
            }
        }
        viewDataBinding.recyclerView.adapter = adapter
    }

}