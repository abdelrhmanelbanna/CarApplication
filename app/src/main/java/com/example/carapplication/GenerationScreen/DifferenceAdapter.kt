package com.example.carapplication.GenerationScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.carapplication.GenerationScreen.VehicleAdapter.ViewHolder
import com.example.carapplication.R
import com.example.carapplication.databinding.ItemDifferenceBinding
import com.example.carapplication.databinding.ItemVehicleBinding
import com.example.domain.model.Vehicle

class DifferenceAdapter (var items:List<String?>?=null) : RecyclerView.Adapter<DifferenceAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder{
        val viewBinding  : ItemDifferenceBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_difference,parent,false)

        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {

        val item = items?.get(position)

        holder.bind(item)

    }

    fun changeData(difference: List<String?>?){

        items = difference
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items?.size?:0
    }

    class ViewHolder(val itemBinding : ItemDifferenceBinding)
        : RecyclerView.ViewHolder(itemBinding.root){

        fun bind(item: String?){
            itemBinding.bind = item
            itemBinding.invalidateAll()
        }

    }
}