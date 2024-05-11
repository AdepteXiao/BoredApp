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
import com.example.test.data.toHistoryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class FavVModel(private val database: Db) : ViewModel() {

    var favList: List<FavEntity> by mutableStateOf(mutableListOf())
    private fun getList() {
        viewModelScope.launch {
            val lst = database.favDao.getAllFav().firstOrNull() ?: emptyList()
            favList = lst.map { it }
        }
    }

    fun moveItem(item: FavEntity) {
        viewModelScope.launch {
            database.favDao.delete(item)
            val historyItem = item.toHistoryEntity()
            database.historyDao.insert(historyItem)
            getList()
        }
    }

    fun deleteItem(item: FavEntity){
        viewModelScope.launch {
            database.favDao.delete(item)
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
                return FavVModel(database) as T
            }
        }
    }

    init {
        getList()
    }
}