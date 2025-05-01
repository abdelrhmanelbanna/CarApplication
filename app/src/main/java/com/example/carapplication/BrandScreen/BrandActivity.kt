package com.example.carapplication.BrandScreen

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.carapplication.ModelScreen.ModelActivity
import com.example.carapplication.R
import com.example.carapplication.databinding.ActivityBrandBinding
import com.example.domain.model.Brand
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
        setUpObservers()
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

    private fun setUpObservers() {
        viewModel.brandsLiveData.observe(this, { brandsResponse ->
            adapter.changeData(brandsResponse)
        })
    }

    private fun setUpAdapter() {
        adapter.onItemClickListener = object : BrandAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, item: Brand?) {
                // nav to Model Screen
                val intent = Intent(this@BrandActivity, ModelActivity::class.java)
                intent.putExtra("brand_id", item?.id)
                startActivity(intent)
            }
        }
        viewDataBinding.recyclerView.adapter = adapter
    }

}