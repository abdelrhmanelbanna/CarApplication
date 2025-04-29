package com.example.data.model.models

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.example.domain.model.Models
import com.google.gson.annotations.SerializedName


data class ModelsDto(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("identification_attribute_value_id")
	val identificationAttributeValueId: Int? = null,

	@field:SerializedName("parent_brand_image")
	val parentBrandImage: String? = null,

	@field:SerializedName("parent_brand")
	val parentBrand: String? = null,

	@field:SerializedName("hidden_price")
	val hiddenPrice: Int? = null,

	@field:SerializedName("least_deposit")
	val leastDeposit: Int? = null,

	@field:SerializedName("attributes_hint_list")
	val attributesHintList: List<AttributesHintListItem?>? = null,

	@field:SerializedName("least_installment")
	val leastInstallment: Int? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("attribute")
	val attribute: String? = null,

	@field:SerializedName("attribute_image")
	val attributeImage: String? = null,

	@field:SerializedName("attribute_value")
	val attributeValue: String? = null,

	@field:SerializedName("vehicle_id")
	val vehicleId: Int? = null,

	@field:SerializedName("identification_attribute_id")
	val identificationAttributeId: Int? = null
){
	fun toModels():Models{
		return Models(
			image=image,
			identificationAttributeValueId=identificationAttributeValueId,
			parentBrandImage=parentBrandImage,
			parentBrand=parentBrand,
			hiddenPrice=hiddenPrice,
			leastDeposit=leastDeposit,
			leastInstallment=leastInstallment,
			price=price,
			name=name,
			id=id,
			attribute=attribute,
			attributeImage=attributeImage,
			attributeValue=attributeValue,
			vehicleId=vehicleId,
			identificationAttributeId=identificationAttributeId

		)
	}
}