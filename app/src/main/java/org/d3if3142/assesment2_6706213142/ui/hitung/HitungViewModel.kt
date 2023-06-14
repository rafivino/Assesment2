package org.d3if3142.assesment2_6706213142.ui.hitung

import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3142.assesment2_6706213142.R
import org.d3if3142.assesment2_6706213142.data.Ubin
import org.d3if3142.assesment2_6706213142.databinding.HitungFragmentBinding
import org.d3if3142.assesment2_6706213142.db.HasilDao
import org.d3if3142.assesment2_6706213142.db.HasilDb
import org.d3if3142.assesment2_6706213142.db.HasilEntity
import org.d3if3142.assesment2_6706213142.model.HasilLuas
import org.d3if3142.assesment2_6706213142.model.hitungsisi
import org.d3if3142.assesment2_6706213142.network.UbinApi

class HitungViewModel(private val db: HasilDao) : ViewModel() {
    private val hasilsisi = MutableLiveData<HasilLuas?>()

    fun hitungSisi(sisi: Float){
        val datahasil = HasilEntity(
            sisi = sisi,
        )

        hasilsisi.value = datahasil.hitungsisi()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(datahasil)
            }
        }
    }

    fun getHasil(): LiveData<HasilLuas?> = hasilsisi
}