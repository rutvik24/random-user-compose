package com.moontowertickets.randomuserdemo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moontowertickets.randomuserdemo.domain.repository.UserRepository
import com.moontowertickets.randomuserdemo.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    var state by mutableStateOf(UserState())
        private set

    fun loadUsers(page: Int?  = 1, results: Int? = 20) {
        viewModelScope.launch {
            state = when(val result = userRepository.getUsers(page = page, results = results)) {
                is Resource.Success -> {
                    state.copy(
                        users = result.data?.results ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }

                is Resource.Error -> {
                    state.copy(
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }
}