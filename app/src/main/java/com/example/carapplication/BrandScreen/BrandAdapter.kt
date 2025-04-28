package com.example.carapplication.BrandScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.carapplication.R
import com.example.carapplication.databinding.ItemBrandsBinding
import com.example.domain.model.Brand

class BrandAdapter(var items:List<Brand?>?=null)
    :RecyclerView.Adapter<BrandAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding  : ItemBrandsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_brands,parent,false)

        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items?.get(position)

        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items?.size?:0
    }

    fun changeData(brandsResponse: List<Brand?>?){

        items = brandsResponse
        notifyDataSetChanged()
    }


    class ViewHolder(val itemBinding :ItemBrandsBinding)
        :RecyclerView.ViewHolder(itemBinding.root){

            fun bind(item:Brand?){
                itemBinding.binding = item
                itemBinding.invalidateAll()
            }

    }
}