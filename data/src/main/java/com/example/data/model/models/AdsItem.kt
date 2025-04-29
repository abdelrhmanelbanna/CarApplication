package com.example.data.model.models

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class AdsItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("position")
	val position: Int? = null,

	@field:SerializedName("url")
	val url: String? = null
)