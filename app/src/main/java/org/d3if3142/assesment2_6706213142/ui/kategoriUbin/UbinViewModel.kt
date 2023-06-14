package org.d3if3142.assesment2_6706213142.ui.kategoriUbin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3142.assesment2_6706213142.data.Ubin
import org.d3if3142.assesment2_6706213142.network.ApiStatus
import org.d3if3142.assesment2_6706213142.network.UbinApi

class UbinViewModel : ViewModel(){
    private val data = MutableLiveData<List<Ubin>>()
    private val status = MutableLiveData<ApiStatus>()

    init {

        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch ( Dispatchers.IO ) {
            status.postValue(ApiStatus.LOADING)
            try {
                data.postValue(UbinApi.service.getUbin())
                status.postValue(ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("UbinViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }

    fun getData(): LiveData<List<Ubin>> = data
    fun getStatus(): LiveData<ApiStatus> = status
}