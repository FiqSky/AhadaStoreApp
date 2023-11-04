/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fiqsky.ahadastoreapp.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.fiqsky.ahadastoreapp.StoreApplication
import com.fiqsky.ahadastoreapp.ui.home.HomeViewModel
import com.fiqsky.ahadastoreapp.ui.item.ItemDetailsViewModel
import com.fiqsky.ahadastoreapp.ui.item.ItemEditViewModel
import com.fiqsky.ahadastoreapp.ui.item.ItemEntryViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Store app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemEditViewModel
        initializer {
            ItemEditViewModel(
                this.createSavedStateHandle(),
                storeApplication().container.itemsRepository
            )
        }
        // Initializer for ItemEntryViewModel
        initializer {
            ItemEntryViewModel(storeApplication().container.itemsRepository)
        }

        // Initializer for ItemDetailsViewModel
        initializer {
            ItemDetailsViewModel(
                this.createSavedStateHandle(),
                storeApplication().container.itemsRepository
            )
        }

        // Initializer for HomeViewModel
        initializer {
            HomeViewModel(storeApplication().container.itemsRepository)
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [StoreApplication].
 */
fun CreationExtras.storeApplication(): StoreApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as StoreApplication)
