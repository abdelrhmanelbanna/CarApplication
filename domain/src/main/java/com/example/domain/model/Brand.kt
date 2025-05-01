package com.example.domain.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Brand(
	val image: String = "",
	val name: String = "",
	val id: Int = 0
) : Parcelable
