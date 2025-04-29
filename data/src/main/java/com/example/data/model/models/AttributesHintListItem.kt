package com.example.data.model.models

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class AttributesHintListItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("attribute")
	val attribute: String? = null,

	@field:SerializedName("value")
	val value: String? = null
)