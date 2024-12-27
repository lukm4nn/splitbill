package com.qti.splitbill.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.qti.splitbill.entity.SplitBill

@Dao
interface SplitBillDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSplitBill(splitBill: SplitBill)

    @Update
    suspend fun updateSplitBill(splitBill: SplitBill)

    @Query("SELECT * FROM split_bill WHERE id = :id")
    suspend fun getSplitBillById(id: String): SplitBill?

    @Query("SELECT * FROM split_bill")
    suspend fun getAllSplitBills(): List<SplitBill>

    @Delete
    suspend fun deleteSplitBill(splitBill: SplitBill)
}
