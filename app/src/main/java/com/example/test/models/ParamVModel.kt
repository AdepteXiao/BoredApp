package com.example.test.models

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.test.api.Api
import com.example.test.bottom_nav.Route
import com.example.test.data.Db
import com.example.test.data.FavEntity
import kotlinx.coroutines.launch

class ParamVModel(
    private val navHostController: NavHostController,
    private val database: Db
) : ViewModel() {

    var type: String by mutableStateOf("any")
    var people: String by mutableStateOf("any")
    var price: String by mutableStateOf("any")
    var activityIdea: String by mutableStateOf("any")
    var typeIdea: String by mutableStateOf("any")
    var peopleIdea: Int by mutableIntStateOf(1)
    var priceIdea: String by mutableStateOf("any")
    var isLoading: Boolean by mutableStateOf(false)


    fun setSelectedType(choice: String) {
        type = choice
    }

    fun setSelectedPeople(choice: String) {
        people = choice
    }

    fun setSelectedPrice(choice: String) {
        price = choice
    }

    fun generateIdeas(context: Context, isNavToPage: Boolean = true) {
        viewModelScope.launch {
            isLoading = true
            try {
                Log.d("API", "try to get by $type, $people, $price")
                val result = Api.call(type, people, price)
                if (result != null) {
                    if (result.activity != null) {
                        activityIdea = result.activity
                        typeIdea = result.type!!
                        peopleIdea = result.participants
                        priceIdea = Api.apiPriceToUserPrice(result.price)
                        Log.d("API", context.toString())
                        if (isNavToPage) {
                            navHostController.navigate(Route.IdeaScreen)
                        }
                    } else {
                        Toast.makeText(
                            context, "We dont have any ideas for this parameters :(",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    Log.d("VM API", "got $result")
                }
            } catch (e: Exception) {
                Log.e("VM API", "Error generating activity", e)
            } finally {
                isLoading = false
            }
        }
    }

    fun saveGenerateIdeas(context: Context) {
        viewModelScope.launch {
            try {
                generateIdeas(context, isNavToPage = false)
                val entity = FavEntity(null, activityIdea, typeIdea, peopleIdea.toString(), priceIdea)
                database.favDao.insert(entity)
            } catch (e: Exception) {
                //
            }
        }
    }

//    companion object {
//        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
//            @Suppress("UNCHECKED_CAST")
//            override fun <T : ViewModel> create(
//                modelClass: Class<T>,
//                extras: CreationExtras
//            ): T {
//                val database =
//                    (checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as App).database
//                return ParamVModel(database) as T
//            }
//        }
//    }
}