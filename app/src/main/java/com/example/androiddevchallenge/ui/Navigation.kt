/*
 * Copyright 2021 The Android Open Source Project
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
package com.example.androiddevchallenge.ui

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.utils.getMutableStateOf

enum class ScreenName { HOME, DETAIL }

private const val SIS_PUPPY_ID = "puppy_id"
private const val SIS_NAME = "screen_name"

sealed class Screen(val id: ScreenName) {
    object Home : Screen(ScreenName.HOME)
    data class Detail(val puppyId: String) : Screen(ScreenName.DETAIL)
}

private fun Screen.toBundle(): Bundle {
    return bundleOf(SIS_NAME to id.name).also { b ->
        (this as? Screen.Detail)?.let {
            b.putString(SIS_PUPPY_ID, puppyId)
        }
    }
}

private fun Bundle.toScreen(): Screen {
    return when (getString(SIS_NAME)?.let { ScreenName.valueOf(it) }) {
        ScreenName.HOME -> Screen.Home
        ScreenName.DETAIL -> {
            val puppyId = getString(SIS_PUPPY_ID) ?: ""
            Screen.Detail(puppyId)
        }
        else -> {
            Screen.Home
        }
    }
}

class NavigationViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    /**
     * Hold the current screen in an observable, restored from savedStateHandle after process
     * death.
     *
     * mutableStateOf is an observable similar to LiveData that's designed to be read by compose. It
     * supports observability via property delegate syntax as shown here.
     */
    var currentScreen: Screen by savedStateHandle.getMutableStateOf<Screen>(
        key = SIS_PUPPY_ID,
        default = Screen.Home,
        save = { it.toBundle() },
        restore = { it.toScreen() }
    )
        private set // limit the writes to only inside this class.

    /**
     * Go back (always to [Home]).
     *
     * Returns true if this call caused user-visible navigation. Will always return false
     * when [currentScreen] is [Home].
     */
    @MainThread
    fun onBack(): Boolean {
        val wasHandled = currentScreen != Screen.Home
        currentScreen = Screen.Home
        return wasHandled
    }

    /**
     * Navigate to requested [Screen].
     *
     * If the requested screen is not [Home], it will always create a back stack with one element:
     * ([Home] -> [screen]). More back entries are not supported in this app.
     */
    @MainThread
    fun navigateTo(screen: Screen) {
        currentScreen = screen
    }
}
