package com.example.carapplication.BrandScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.carapplication.R
import com.example.carapplication.databinding.ItemBrandsBinding
import com.example.domain.model.Brand

class BrandAdapter :
    ListAdapter<Brand, BrandAdapter.BrandViewHolder>(BrandDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val viewBinding: ItemBrandsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_brands, parent, false
        )
        return BrandViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        val brand = getItem(position)
        holder.bind(brand)
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(position, brand)
        }
    }

    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int, item: Brand?)
    }

    fun changeData(newBrands: List<Brand>) {
        submitList(newBrands)
    }

    class BrandViewHolder(private val itemBinding: ItemBrandsBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: Brand?) {
            itemBinding.binding = item
            itemBinding.invalidateAll()
        }

    }

    class BrandDiffCallback : DiffUtil.ItemCallback<Brand>() {
        override fun areItemsTheSame(oldItem: Brand, newItem: Brand): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Brand, newItem: Brand): Boolean {
            return oldItem == newItem
        }
    }
}