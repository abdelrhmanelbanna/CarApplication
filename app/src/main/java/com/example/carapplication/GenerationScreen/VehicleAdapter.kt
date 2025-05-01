package com.example.carapplication.GenerationScreen

import android.view.LayoutInflater
import android.view.View
import android.content.Context
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.carapplication.R
import com.example.carapplication.databinding.ItemVehicleBinding
import com.example.domain.model.Vehicle

class VehicleAdapter (var items:List<Vehicle?>?=null)
    : RecyclerView.Adapter<VehicleAdapter.ViewHolder>() {

    private val clickedItems = mutableSetOf<Int>()
    private var selectedFavouritePosition: Int? = null
    private var selectedComparePosition: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val viewBinding  : ItemVehicleBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_vehicle,parent,false)

        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.bind(item)

        val context = holder.itemView.context

        val differenceAdapter = DifferenceAdapter()
        differenceAdapter.changeData(item?.extraAttributes)
        holder.itemBinding.differenceRecyclerView.adapter = differenceAdapter

        val isClicked = clickedItems.contains(position)

        val iconColor = if (isClicked) R.color.primary else R.color.gray
        holder.itemBinding.icDifferent.setColorFilter(context.getColor(iconColor))
        holder.itemBinding.differenceTv.setTextColor(context.getColor(iconColor))

        holder.itemBinding.icArrow.setImageResource(
            if (isClicked) R.drawable.ic_up else R.drawable.ic_down
        )

        holder.itemBinding.differenceRecyclerView.visibility =
            if (isClicked) View.VISIBLE else View.GONE

        holder.itemBinding.differenceComponent.setOnClickListener {
            onDifferenceClickListener?.onDifferenceClick(position, item)
            if (clickedItems.contains(position)) {
                clickedItems.remove(position)
            } else {
                clickedItems.add(position)
            }
            notifyItemChanged(position)
        }

        // Favourite icon
        val favColor = if (selectedFavouritePosition == position) R.color.primary else R.color.gray
        holder.itemBinding.icFavourite.setColorFilter(context.getColor(favColor))

        // Compare icon
        val compareColor = if (selectedComparePosition == position) R.color.primary else R.color.gray
        holder.itemBinding.icCompare.setColorFilter(context.getColor(compareColor))

        holder.itemBinding.icFavourite.setOnClickListener {
            val prevSelected = selectedFavouritePosition
            selectedFavouritePosition = if (selectedFavouritePosition == position) null else position
            notifyItemChanged(prevSelected ?: -1)
            notifyItemChanged(position)
            onFavouriteClickListener?.onFavouriteClick(position, item)
        }

        holder.itemBinding.icCompare.setOnClickListener {
            val prevSelected = selectedComparePosition
            selectedComparePosition = if (selectedComparePosition == position) null else position
            notifyItemChanged(prevSelected ?: -1)
            notifyItemChanged(position)
            onCompareClickListener?.onCompareClick(position, item)
        }
    }

    var onFavouriteClickListener:OnFavouriteClickListener?=null
    interface OnFavouriteClickListener{
        fun onFavouriteClick(pos:Int , item: Vehicle?)
    }

    var onCompareClickListener:OnCompareClickListener?=null
    interface OnCompareClickListener{
        fun onCompareClick(pos:Int , item: Vehicle?)
    }

    var onDifferenceClickListener :OnDifferenceClickListener?=null
    interface OnDifferenceClickListener{

        fun onDifferenceClick(pos:Int , item: Vehicle?)
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