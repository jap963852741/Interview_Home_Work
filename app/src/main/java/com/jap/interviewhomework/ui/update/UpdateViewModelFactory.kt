package com.jap.interviewhomework.ui.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jap.interviewhomework.data.NewsDataSource
import com.jap.interviewhomework.data.NewsRepository

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class UpdateViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpdateViewModel::class.java)) {
            return UpdateViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}