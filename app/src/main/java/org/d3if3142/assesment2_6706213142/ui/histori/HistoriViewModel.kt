package org.d3if3142.assesment2_6706213142.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3142.assesment2_6706213142.db.HasilDao

class HistoriViewModel(private val db: HasilDao) : ViewModel() {
    val data = db.getLashHasil()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO){
            db.clearData()
        }
    }
}