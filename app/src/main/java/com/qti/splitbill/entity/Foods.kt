package com.qti.splitbill.entity

data class Foods(
    var nama_foods: String,
    var price_foods: Double,
    var isDiscount: Boolean,
    var discount_foods: Double,
    var qty: Int
)