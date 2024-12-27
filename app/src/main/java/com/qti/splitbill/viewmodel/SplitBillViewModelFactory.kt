package com.thegamers.pokemonapi.viewmodel

import SplitBillViewModel
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class SplitBillViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplitBillViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SplitBillViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}