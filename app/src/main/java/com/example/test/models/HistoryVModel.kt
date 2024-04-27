package com.example.test.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.test.App
import com.example.test.data.Db
import com.example.test.data.FavEntity
import com.example.test.data.HistoryEntity
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class HistoryVModel(private val database: Db)   : ViewModel() {
    var activity = "any"
    var type = "any"
    var participants = "any"
    var price = "any"
    var note = "How did it go?"
    var location: String? = null


    private var historyList: List<HistoryEntity> by mutableStateOf(mutableListOf())
    fun getList(): List<HistoryEntity> {
        viewModelScope.launch {
            val lst = database.historyDao.getAllHistory().firstOrNull() ?: emptyList()
            historyList = lst.map { it }}
        return historyList
    }

    companion object{
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val database = (checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as App).database
                return HistoryVModel(database) as T
            }
        }
    }
}