package org.d3if3142.assesment2_6706213142.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HasilEntity::class], version = 1, exportSchema = false)
abstract class HasilDb: RoomDatabase() {
    abstract val dao:HasilDao
    companion object {
        @Volatile
        private var INSTANCE: HasilDb? = null

        fun getInstance(context: Context): HasilDb {
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HasilDb::class.java,
                        "hasilsisi.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}