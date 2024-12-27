package com.qti.splitbill.entity

data class ResultScan(
    var restoName: String,
    var totalBill: Double,
    var discountBill: Double,
    var pajak: Double,
    var ongkir: Double,
    var Tips: Double,
    var listResultFoods: List<ResultScanFoods>
)
