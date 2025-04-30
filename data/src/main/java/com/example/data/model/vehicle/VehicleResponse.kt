package com.example.data.model.vehicle

import com.example.domain.model.Vehicle
import com.google.gson.annotations.SerializedName

data class VehicleResponse(

	@field:SerializedName("data")
	val data: List<VehicleDto?>? = null
)

data class VehicleDto(

	@field:SerializedName("additional_images")
	val additionalImages: List<String?>? = null,

	@field:SerializedName("generation")
	val generation: String? = null,

	@field:SerializedName("year")
	val year: String? = null,

	@field:SerializedName("model_image")
	val modelImage: String? = null,

	@field:SerializedName("model_id")
	val modelId: Int? = null,

	@field:SerializedName("stock_available")
	val stockAvailable: Boolean? = null,

	@field:SerializedName("in_compare_list")
	val inCompareList: Boolean? = null,

	@field:SerializedName("brand_id")
	val brandId: Int? = null,

	@field:SerializedName("is_wishlisted")
	val isWishlisted: Boolean? = null,

	@field:SerializedName("hidden_price")
	val hiddenPrice: Int? = null,

	@field:SerializedName("brand_image")
	val brandImage: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("selling_type")
	val sellingType: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("model")
	val model: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("brand")
	val brand: String? = null,

	@field:SerializedName("has_seller")
	val hasSeller: Boolean? = null,

	@field:SerializedName("extra_attributes")
	val extraAttributes: List<String?>? = null
){

	fun toVehicle():Vehicle{
		return  Vehicle(
			 additionalImages = additionalImages,
			 generation=generation,
			 year=year,
			 modelImage=modelImage,
			 modelId=modelId,
			 stockAvailable=stockAvailable,
			 brandId=brandId,
			 isWishlisted=isWishlisted,
			 hiddenPrice=hiddenPrice,
			 brandImage= brandImage,
			 price=price,
			 sellingType = sellingType,
			 name=name,
			 model=model,
			 id=id,
			 brand=brand,
			 hasSeller=hasSeller,
			 extraAttributes=extraAttributes
		)
	}
}
