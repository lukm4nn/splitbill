package com.qti.splitbill.entity

import androidx.room.TypeConverters

data class MemberSplitBill(
    val nama: String,
    val total_belanja: Double,
    val isDiscount: Boolean,
    val discount: Double = 0.0,
    val isOngkir: Boolean,
    val ongkir: Double = 0.0,
    val isDiscountOngkir: Boolean,
    val discountOngkir: Double = 0.0,
    val isPajak: Boolean,
    val pajak: Double = 0.0,
    @TypeConverters(FoodConverter::class)
    val list_foods: List<Foods>,
    val isBayar: Boolean,
    val isViewer: Boolean
)
