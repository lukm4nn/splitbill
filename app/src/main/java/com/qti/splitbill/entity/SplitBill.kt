package com.qti.splitbill.entity

import androidx.room.*

@Entity(tableName = "split_bill")
data class SplitBill(
    @PrimaryKey val id: String,
    val title_bill: String,
    val total_bill: Double,
    val waktu: String,
    val isDiscount: Boolean,
    val discount: Double = 0.0,
    val isOngkir: Boolean,
    val ongkir: Double = 0.0,
    val isDiscountOngkir: Boolean,
    val discountOngkir: Double = 0.0,
    val isPajak: Boolean,
    val pajak: Double = 0.0,
    @TypeConverters(MemberConverter::class)
    val listmember_split_bill: List<MemberSplitBill>
)




