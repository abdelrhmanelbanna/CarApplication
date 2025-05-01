package com.example.carapplication.GenerationScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.carapplication.BrandScreen.BrandAdapter.ViewHolder
import com.example.carapplication.R
import com.example.carapplication.databinding.ItemBrandsBinding
import com.example.carapplication.databinding.ItemVehicleBinding
import com.example.domain.model.Brand
import com.example.domain.model.Vehicle

class VehicleAdapter (var items:List<Vehicle?>?=null)
    : RecyclerView.Adapter<VehicleAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val viewBinding  : ItemVehicleBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_vehicle,parent,false)

        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)

        holder.bind(item)

    }

    fun changeData(vehicleResponse: List<Vehicle?>?){

        items = vehicleResponse
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items?.size?:0
    }

    class ViewHolder(val itemBinding : ItemVehicleBinding)
        :RecyclerView.ViewHolder(itemBinding.root){

        fun bind(item: Vehicle?){
            itemBinding.binding = item
            itemBinding.invalidateAll()
        }

    }
}