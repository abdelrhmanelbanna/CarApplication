package com.example.carapplication.ModelScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.carapplication.R
import com.example.carapplication.databinding.ItemBrandsBinding
import com.example.carapplication.databinding.ItemModelGrideBinding
import com.example.carapplication.databinding.ItemModelListBinding
import com.example.domain.model.Brand
import com.example.domain.model.Models

class ModelAdapter(
    private var items: List<Models?>? = null,
    private var isGrid: Boolean = true
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_GRID = 1
        private const val VIEW_TYPE_LIST = 2
    }

    fun setViewType(grid: Boolean) {
        isGrid = grid
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (isGrid) VIEW_TYPE_GRID else VIEW_TYPE_LIST
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_GRID) {
            val binding: ItemModelGrideBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_model_gride,
                parent,
                false
            )
            GridViewHolder(binding)
        } else {
            val binding: ItemModelListBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_model_list,
                parent,
                false
            )
            ListViewHolder(binding)
        }
    }

    override fun getItemCount(): Int = items?.size ?: 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items?.get(position)
        when (holder) {
            is GridViewHolder -> holder.bind(item)
            is ListViewHolder -> holder.bind(item)
        }
    }



    fun changeData(modelsResponse: List<Models?>?) {
        items = modelsResponse
        notifyDataSetChanged()
    }

    class GridViewHolder(private val binding: ItemModelGrideBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Models?) {
            binding.binding = item
            binding.invalidateAll()
        }
    }

    class ListViewHolder(private val binding: ItemModelListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Models?) {
            binding.binding = item
            binding.invalidateAll()
        }
    }
}