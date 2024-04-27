package com.example.test.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.test.api.callAPi
import com.example.test.App
import com.example.test.data.Db
import com.example.test.data.FavEntity
import kotlinx.coroutines.launch

class ParamVModel(private val database: Db) : ViewModel() {

    var type = "any"
    var people = "any"
    var price = "any"
    var activityIdea = "any"
    var typeIdea = "any"
    var peopleIdea = 1
    var priceIdea: Double = 0.0

    fun setSelectedType(choice: String) {
        type = choice
    }

    fun setSelectedPeople(choice: String) {
        people = choice
    }

    fun setSelectedPrice(choice: String) {
        price = choice
    }

    fun generateIdeas() {
        viewModelScope.launch {
            try {
                val results = callAPi(type, people, price)
                activityIdea = results.activity
                typeIdea = results.type
                peopleIdea = results.participants
                priceIdea = results.price
            } catch (e: Exception) {
                //
            }
        }
    }

    fun saveGenerateIdeas() {
        viewModelScope.launch {
            try {
                generateIdeas()
                val entity = FavEntity(null, activityIdea, typeIdea, peopleIdea.toString(), price)
                database.favDao.insert(entity)
            } catch (e: Exception) {
                //
            }
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
                return ParamVModel(database) as T
            }
        }
    }
}