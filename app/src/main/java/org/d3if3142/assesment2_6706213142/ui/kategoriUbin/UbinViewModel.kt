package org.d3if3142.assesment2_6706213142.ui.kategoriUbin

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3142.assesment2_6706213142.data.Ubin
import org.d3if3142.assesment2_6706213142.network.ApiStatus
import org.d3if3142.assesment2_6706213142.network.UbinApi
import org.d3if3142.assesment2_6706213142.network.UpdateWorker
import java.util.concurrent.TimeUnit

class UbinViewModel : ViewModel(){
    private val data = MutableLiveData<List<Ubin>>()
    private val status = MutableLiveData<ApiStatus>()

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(app).enqueueUniqueWork(
            UpdateWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
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