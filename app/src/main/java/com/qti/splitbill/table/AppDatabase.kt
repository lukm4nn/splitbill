package com.qti.splitbill.table

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.qti.splitbill.entity.FoodConverter
import com.qti.splitbill.entity.MemberConverter
import com.qti.splitbill.entity.SplitBill
import com.qti.splitbill.repository.SplitBillDao

import android.content.Context
import androidx.room.Room
import com.qti.splitbill.entity.InfoMember
import com.qti.splitbill.repository.MemberDao

@Database(
    entities = [SplitBill::class, InfoMember::class], // Tambahkan Payment
    version = 2, // Update versi database
    exportSchema = false
)
@TypeConverters(MemberConverter::class, FoodConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun splitBillDao(): SplitBillDao
    abstract fun memberDao(): MemberDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "split_bill_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
