package com.qti.splitbill.entity

data class ResultScanFoods(
    var itemName: String,
    var qty: Int,
    var priceNormal: Double,
    var priceDiscount: Double
)
