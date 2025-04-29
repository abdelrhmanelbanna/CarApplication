package com.example.carapplication.ModelScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.carapplication.R
import com.example.carapplication.databinding.ItemBrandsBinding
import com.example.carapplication.databinding.ItemModelGrideBinding
import com.example.carapplication.databinding.ItemModelListBinding
import com.example.domain.model.Brand
import com.example.domain.model.Models

class ModelAdapter(var items:List<Models?>?=null)
    :RecyclerView.Adapter<ModelAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val viewBinding:ItemModelGrideBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_model_gride,parent,false)

        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items?.get(position)

        holder.bind(item)

    }


    override fun getItemCount(): Int {
        return items?.size?:0
    }

    fun changeData(modelsResponse: List<Models?>?){

        items = modelsResponse
        notifyDataSetChanged()
    }


    class ViewHolder(val itemBinding :ItemModelGrideBinding)
        :RecyclerView.ViewHolder(itemBinding.root){

            fun bind(item:Models?){
                itemBinding.binding = item
                itemBinding.invalidateAll()
            }

    }
}