package com.example.carapplication.ModelScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.carapplication.R
import com.example.carapplication.databinding.ItemModelGridBinding
import com.example.carapplication.databinding.ItemModelListBinding
import com.example.domain.model.Models


class ModelAdapter(private var isGrid: Boolean) : ListAdapter<Models, RecyclerView.ViewHolder>(ModelDiffCallback()) {

    companion object {
        private const val VIEW_TYPE_GRID = 1
        private const val VIEW_TYPE_LIST = 2
    }

    var onItemClickListener: OnItemClickListener? = null

    override fun getItemViewType(position: Int) = if (isGrid) VIEW_TYPE_GRID else VIEW_TYPE_LIST


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_GRID -> {
                val binding: ItemModelGridBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context), R.layout.item_model_grid, parent, false
                )
                GridViewHolder(binding)
            }
            else  -> {
                val binding: ItemModelListBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context), R.layout.item_model_list, parent, false
                )
                ListViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is GridViewHolder -> holder.bind(item)
            is ListViewHolder -> holder.bind(item)
        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(item)
        }
    }

    fun setViewType(grid: Boolean) {
        isGrid = grid
        notifyDataSetChanged()
    }


    interface OnItemClickListener {
        fun onItemClick(item: Models?)
    }

    class GridViewHolder(private val binding: ItemModelGridBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Models?) {
            binding.binding = item
            binding.invalidateAll()
        }
    }

    class ListViewHolder(private val binding: ItemModelListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Models?) {
            binding.binding = item
            binding.invalidateAll()
        }
    }
}

class ModelDiffCallback : DiffUtil.ItemCallback<Models>() {
    override fun areItemsTheSame(oldItem: Models, newItem: Models): Boolean {
        return oldItem.vehicleId == newItem.vehicleId
    }

    override fun areContentsTheSame(oldItem: Models, newItem: Models): Boolean {
        return oldItem == newItem
    }
}