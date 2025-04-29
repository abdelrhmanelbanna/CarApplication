package com.example.domain.model



data class AdsItem(
	val image: String? = null,
	val id: Int? = null,
	val position: Int? = null,
	val url: String? = null
)

data class Models(
	val image: String? = null,
	val identificationAttributeValueId: Int? = null,
	val parentBrandImage: String? = null,
	val parentBrand: String? = null,
	val hiddenPrice: Int? = null,
	val leastDeposit: Double? = null,
	val attributesHintList: List<AttributesHintListItem?>? = null,
	val leastInstallment: Int? = null,
	val price: Int? = null,
	val name: String? = null,
	val id: Int? = null,
	val attribute: String? = null,
	val attributeImage: String? = null,
	val attributeValue: String? = null,
	val vehicleId: Int? = null,
	val identificationAttributeId: Int? = null
)

data class AttributesHintListItem(
	val image: String? = null,
	val attribute: String? = null,
	val value: String? = null
)

