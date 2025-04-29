package com.example.carapplication.ModelScreen

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carapplication.R
import com.example.carapplication.databinding.ActivityModelBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ModelActivity : AppCompatActivity() {

    lateinit var viewModel: ModelsViewModel
    lateinit var viewBinding : ActivityModelBinding
    private lateinit var adapter: ModelAdapter
    private var isGrid = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ModelsViewModel::class.java)
        viewBinding = DataBindingUtil.setContentView(this,R.layout.activity_model)

        val brandId = intent.getIntExtra("brand_id", -1)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        adapter = ModelAdapter(isGrid = isGrid)
        viewBinding.recyclerView.adapter = adapter
        viewBinding.recyclerView.layoutManager = GridLayoutManager(this, 2)

        viewModel.modelsLivedata.observe(this) { modelsResponse ->
            adapter.changeData(modelsResponse)
        }

        viewBinding.icGride.setOnClickListener {
            isGrid = !isGrid

            adapter.setViewType(isGrid)

            // update layout manager if needed
            viewBinding.recyclerView.layoutManager = if (isGrid) {
                GridLayoutManager(this, 2)
            } else {
                LinearLayoutManager(this)
            }

            val iconRes = if (isGrid) R.drawable.ic_list else R.drawable.ic_grid
            viewBinding.icGride.setImageResource(iconRes)
        }

        viewModel.getModels(brandId)
    }
}