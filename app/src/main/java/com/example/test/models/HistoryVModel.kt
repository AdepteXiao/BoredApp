package com.example.test.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.test.App
import com.example.test.data.Db
import com.example.test.data.HistoryEntity
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MarkerState
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class HistoryVModel(private val database: Db) : ViewModel() {
    var activity = "any"
    var type = "any"
    var location = " "
    var historyList: List<HistoryEntity> by mutableStateOf(mutableListOf())
    var start_location = LatLng(1.35, 103.87)
    var curLatLng: LatLng by mutableStateOf(start_location)
    var cameraPosition: CameraPosition by mutableStateOf(
        CameraPosition.fromLatLngZoom(
            start_location,
            15f
        )
    )
    var currentItem: HistoryEntity? by mutableStateOf(null)


    fun initialiseMap(hisItem: HistoryEntity) {
        currentItem = hisItem
        if (hisItem.location != null) {
            start_location = convertStringToLatLng(hisItem.location!!)
        }
    }

    fun submitOnMap() {
        currentItem!!.location = "${curLatLng.latitude},${curLatLng.longitude}"
        viewModelScope.launch {
            database.historyDao.update(currentItem!!)
        }
    }

    fun getList() {
        viewModelScope.launch {
            val lst = database.historyDao.getAllHistory().firstOrNull() ?: emptyList()
            historyList = lst.map { it }
        }
    }

    fun updateItem(item: HistoryEntity) {
        viewModelScope.launch {
            database.historyDao.update(item)
        }
    }

    fun deleteItem(item: HistoryEntity) {
        viewModelScope.launch {
            database.historyDao.delete(item)
            getList()
        }
    }


    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val database =
                    (checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as App).database
                return HistoryVModel(database) as T
            }
        }
    }

    fun convertStringToLatLng(location: String): LatLng {
        val parts = location.split(",")
        val latitude = parts[0].trim().toDouble()
        val longitude = parts[1].trim().toDouble()
        return LatLng(latitude, longitude)
    }

    init {
        getList()
    }
}