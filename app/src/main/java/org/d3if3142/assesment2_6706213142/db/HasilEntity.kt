package org.d3if3142.assesment2_6706213142.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hasilsisi")
data class HasilEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val tanggal: Long = System.currentTimeMillis(),
    val sisi: Float
)
