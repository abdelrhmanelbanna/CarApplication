package com.example.domain.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable


@Parcelize
data class Vehicle(
	val additionalImages: List<String?>? = null,
	val generation: String? = null,
	val year: String? = null,
	val modelImage: String? = null,
	val modelId: Int? = null,
	val stockAvailable: Boolean? = null,
	val inCompareList: Boolean? = null,
	val brandId: Int? = null,
	val isWishlisted: Boolean? = null,
	val hiddenPrice: Int? = null,
	val brandImage: String? = null,
	val price: Int? = null,
	val sellingType: String? = null,
	val name: String? = null,
	val model: String? = null,
	val id: Int? = null,
	val brand: String? = null,
	val extraAttributes: List<String?>? = null
) : Parcelable
