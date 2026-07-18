package com.farmmanager.app.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.farmmanager.app.data.repository.FarmRepository

class ViewModelFactory(private val repository: FarmRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(FarmRepository::class.java).newInstance(repository)
    }
}
