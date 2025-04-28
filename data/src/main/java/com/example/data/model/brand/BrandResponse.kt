package com.example.data.model.brand

import com.google.gson.annotations.SerializedName

data class BrandResponse(

	@field:SerializedName("data")
	val data: List<BrandDto?>? = null
)