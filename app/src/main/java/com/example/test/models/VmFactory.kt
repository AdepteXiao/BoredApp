package com.example.test.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.navigation.NavHostController
import com.example.test.App
import com.example.test.data.Db

class VmFactory<VM: ViewModel>(
    private val navHostController: NavHostController,
    private val vmClass: Class<VM>
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        val database =
            (checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as App).database
        return vmClass.getConstructor(NavHostController::class.java, Db::class.java).newInstance(navHostController, database) as T
    }
}