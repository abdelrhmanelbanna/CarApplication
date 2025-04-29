package com.example.data.model.models


import com.google.gson.annotations.SerializedName


data class ModelsResponse(

	@field:SerializedName("ads")
	val ads: List<AdsItem?>? = null,

	@field:SerializedName("data")
	val data: List<ModelsDto?>? = null
)