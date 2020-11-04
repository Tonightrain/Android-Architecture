package com.example.androidarchitecture.viewModel.archtecture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ArchViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ArchViewModel() as T
    }
}