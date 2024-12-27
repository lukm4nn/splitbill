package com.qti.splitbill.entity

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MemberConverter {
    @TypeConverter
    fun fromMemberList(value: List<MemberSplitBill>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toMemberList(value: String): List<MemberSplitBill> {
        val type = object : TypeToken<List<MemberSplitBill>>() {}.type
        return Gson().fromJson(value, type)
    }
}

class FoodConverter {
    @TypeConverter
    fun fromFoodList(value: List<Foods>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toFoodList(value: String): List<Foods> {
        val type = object : TypeToken<List<Foods>>() {}.type
        return Gson().fromJson(value, type)
    }
}
