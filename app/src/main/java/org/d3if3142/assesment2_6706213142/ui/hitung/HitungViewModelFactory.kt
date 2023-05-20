package org.d3if3142.assesment2_6706213142.ui.hitung

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3142.assesment2_6706213142.db.HasilDao

class HitungViewModelFactory (
    private val db: HasilDao
): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T: ViewModel> create (modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HitungViewModel::class.java)) {
            return HitungViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}