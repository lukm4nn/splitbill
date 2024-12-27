package com.qti.splitbill.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "info_member")
data class InfoMember(
    @PrimaryKey val id: String,
    val nama_member: String,
    val no_telp: String
)