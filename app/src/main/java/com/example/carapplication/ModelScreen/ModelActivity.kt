package com.example.carapplication.ModelScreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carapplication.BrandScreen.BrandActivity
import com.example.carapplication.GenerationScreen.GenerationActivity
import com.example.carapplication.R
import com.example.carapplication.databinding.ActivityModelBinding
import com.example.domain.model.Models
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ModelActivity : AppCompatActivity() {

    private val viewModel: ModelsViewModel by viewModels()
    private lateinit var viewBinding: ActivityModelBinding
    private lateinit var adapter: ModelAdapter
    private var isGrid = true
    private var brandImage:String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_model)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val brandId = intent.getIntExtra("brand_id", -1)
         brandImage = intent.getStringExtra("brand_image")

        viewBinding.binding = brandImage

        setupRecyclerView()
        setupObservers()
        setupGridToggle()
        viewModel.loadModels(brandId)

      navigateToHome()
    }

    private fun navigateToHome() {
        viewBinding.icBack.setOnClickListener{
            val intent = Intent(this,BrandActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        adapter = ModelAdapter(isGrid)
        viewBinding.recyclerView.adapter = adapter
        viewBinding.recyclerView.layoutManager = GridLayoutManager(this, 2)

        // Pagination scroll listener
        viewBinding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as? LinearLayoutManager ?: return
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (viewModel.canLoadMore && !viewModel.isLoading && totalItemCount <= lastVisibleItem + 5) {
                    viewModel.loadNextPage()
                }
            }
        })

        adapter.onItemClickListener = object : ModelAdapter.OnItemClickListener {
            override fun onItemClick(item: Models?) {
                val intent = Intent(this@ModelActivity, GenerationActivity::class.java)
                intent.putExtra("attribute_id", item?.identificationAttributeId)
                intent.putExtra("vehicle_id", item?.vehicleId)
                intent.putExtra("attribute_value_id", item?.identificationAttributeValueId)

                intent.putExtra("brand_image",brandImage)
                startActivity(intent)
            }
        }
    }

    private fun setupObservers() {
        viewModel.modelState.observe(this) { state ->
            viewBinding.progressBar.isVisible = state == ModelState.Loading
            viewBinding.loadingMoreProgressBar.isVisible = viewModel.isLoading
            when (state) {
                is ModelState.Success -> {
                    adapter.submitList(state.models)
                }
                is ModelState.Error -> {
                    viewBinding.progressBar.visibility = View.GONE
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                }
                else -> {

                }
            }
        }
    }

    private fun setupGridToggle() {
        viewBinding.icGride.setOnClickListener {
            isGrid = !isGrid
            adapter.setViewType(isGrid)
            viewBinding.recyclerView.layoutManager = if (isGrid) {
                GridLayoutManager(this, 2)
            } else {
                LinearLayoutManager(this)
            }
            val iconRes = if (isGrid) R.drawable.ic_list else R.drawable.ic_grid
            viewBinding.icGride.setImageResource(iconRes)
        }
    }
}