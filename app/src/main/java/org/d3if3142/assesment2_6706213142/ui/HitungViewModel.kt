package org.d3if3142.assesment2_6706213142.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if3142.assesment2_6706213142.model.HasilLuas

class MainViewModel : ViewModel() {
    private val hasilLuas = MutableLiveData<HasilLuas>()

    fun luasKeramik(input : Float){
        val hasil = input * input
        hasilLuas.value = HasilLuas(hasil)
    }

    fun getHasil(): LiveData<HasilLuas?> = hasilLuas
}