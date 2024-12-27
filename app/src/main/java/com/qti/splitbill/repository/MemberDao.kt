package com.qti.splitbill.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.qti.splitbill.entity.InfoMember
import com.qti.splitbill.entity.SplitBill


@Dao
interface MemberDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMember(infoMember: InfoMember)

    @Update
    suspend fun udpateMember(infoMember: InfoMember)

    @Query("SELECT * FROM info_member WHERE id = :id")
    suspend fun getMemberById(id: String): InfoMember?

    @Query("SELECT * FROM info_member")
    suspend fun getAllMember(): List<InfoMember>

    @Delete
    suspend fun deleteMember(infoMember: InfoMember)

}