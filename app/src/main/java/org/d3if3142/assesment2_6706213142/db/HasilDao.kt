package org.d3if3142.assesment2_6706213142.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HasilDao {
    @Insert
    fun insert(hasil: HasilEntity)

    @Query("SELECT * FROM hasilsisi")
    fun getLashHasil(): LiveData<List<HasilEntity>>

    @Query("DELETE FROM hasilsisi")
    fun clearData()
}