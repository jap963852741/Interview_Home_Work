package com.jap.interviewhomework.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jap.interviewhomework.Repository.network.NewsDataSource
import com.jap.interviewhomework.Repository.NewsRepository

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class HomeViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(
                newsRepository = NewsRepository(
                dataSource = NewsDataSource()
            )) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}