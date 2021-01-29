package com.jap.interviewhomework.ui.update

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.jap.interviewhomework.Repository.network.UpdateDataSource
import com.jap.interviewhomework.Repository.UpdateRepository

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class UpdateViewModelFactory (
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(UpdateViewModel::class.java)) {
            return UpdateViewModel(
                    updateRepository = UpdateRepository(
                        UpdateDataSource()
                    )
                    , savedStateHandle = handle
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}