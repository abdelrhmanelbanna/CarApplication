package com.example.data.model.brand

import com.example.domain.model.Brand
import com.google.gson.annotations.SerializedName

data class BrandDto(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
){
	fun toBrand (): Brand {

		return Brand(
			id = id,
			name = name,
			image = image
		)
	}
}